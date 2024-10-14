package com.is.tietovisa.history;

import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Messagebox;

import com.is.utils.Res;

public class BreakBtnListener implements org.zkoss.zk.ui.event.EventListener {

	@Override
	public void onEvent(Event event) throws Exception {
		// TODO Auto-generated method stub
		Messagebox.show("Вы хотите развязать клиент НСИ и клиент Тието ? ",
				"Соглашение", Messagebox.OK | Messagebox.CANCEL,
				Messagebox.QUESTION, new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						int answer = (Integer) event.getData();
						if (answer == Messagebox.OK) {
							Res res = new Res(0, "Ok"); /*CustomerService.removeT�(un, pwd, lnk_id, alias);*/
							if (res.getCode() != 0) {
								//alert(res.getName());
								return;
							}
							//refreshModel(_starttPageNumber);
						} else
							return;
					}
				});

		
	}
}