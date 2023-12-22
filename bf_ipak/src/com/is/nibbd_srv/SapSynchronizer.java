package com.is.nibbd_srv;

import com.is.base.Dao;
import com.is.clients.dao.ClientDao;
import com.is.clients.models.ClientJ;
import com.is.clients.models.SapLogger;
import com.is.clients.sap.SapHandler;
import com.is.clients.utils.ClientUtil;
import com.is.nibbd.models.NibbdReaction;
import com.is.nibbd.service.NibbdReactionDao;
import com.is.utils.CheckNull;
import org.apache.log4j.Logger;

import java.util.List;

public class SapSynchronizer {
    private static final int SUCCESSFUL = 1;
    private static final Logger logger = Logger.getLogger(SapSynchronizer.class);
    public static final int FAILURE = 2;

    public static void handle() {
        Dao<NibbdReaction> reactionDao = NibbdReactionDao.getInstance();
        Dao<ClientJ> clientDao = ClientDao.getInstance();
        //List<ClientJ> clientsList;
        List<NibbdReaction> list = reactionDao.getList();

        if (list == null || list.isEmpty()) {
            return;
        }
        //logger.info("Nibbd reaction list size -> " + list.size());
        for (NibbdReaction nibbdReaction : list) {
            ClientJ clientJ = clientDao.getItemByStringId(nibbdReaction.getBranch(), nibbdReaction.getId_client());

            try {
                if (clientJ == null)
                    throw new Exception("Nibbd client is null " + nibbdReaction.getBranch() + " " + nibbdReaction.getId_client());

                // Сотрудник отвественный за действие nibbd
                clientJ.setNibbd_emp_id(nibbdReaction.getResponsible_emp());
                // Отправить действие утверждения в SAP
                SapHandler.makeSapRequest(clientJ, null);
                // Обновить состояние отправленного рейса
                reactionDao.update(new NibbdReaction(
                        clientJ.getId_client(),
                        clientJ.getBranch(),
                        SUCCESSFUL));
            } catch (Exception e) {
                logger.error(CheckNull.getPstr(e));
                // Записываем в дельту
                new SapLogger.Builder()
                        .idClient(nibbdReaction.getId_client())
                        .branch(nibbdReaction.getBranch())
                        .idSap(null)
                        .customer_type("02") // Type Client J
                        .action(2) // Action - confirm
                        .message(e.getMessage())
                        .state(SapLogger.STATE_ERROR)
                        .build()
                        .log();
                try {
                    // Помечаем, как проблемного клиента
                    reactionDao.update(new NibbdReaction(
                            nibbdReaction.getId_client(),
                            nibbdReaction.getBranch(),
                            FAILURE));
                }
                catch (Exception e1){
                    logger.error(CheckNull.getPstr(e1));
                }
            }

        }
    }
}
