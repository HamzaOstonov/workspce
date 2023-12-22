package com.is.client_personmap;

import java.util.List;

import com.is.clients.utils.ClientUtil;
import org.apache.log4j.Logger;

import com.is.base.Dao;
import com.is.client_personmap.model.Person;
import com.is.client_personmap.model.PersonMap;
import com.is.clients.dao.DaoFactory;
import com.is.clients.ebp.EbpService;
import com.is.clients.models.ClientJ;
import com.is.customer_.contact.Relationship;
import com.is.customer_.contact.service.ContactActionService;
import com.is.customer_.contact.service.RelationshipActionService;
import com.is.customer_.core.model.Customer;
import com.is.customer_.core.model.SessionAttributes;
import com.is.delta_relations.DeltaRelation;
import com.is.utils.Res;

public class PersonLoadingService {
    private Logger logger = Logger.getLogger(PersonLoadingService.class);

	private static String alias;
	private static String un;
	private static String pw;
	public static SessionAttributes sessionAttributes;

	private PersonLoadingService() {
	}

	private PersonLoadingService(String un, String pw, String alias) {
		this.un = un;
		this.pw = pw;
		this.alias = alias;
		
	}

	public static PersonLoadingService instance() {
		return new PersonLoadingService();
	}

	public static PersonLoadingService instance(String un, String pw, String alias) {
		return new PersonLoadingService(un, pw, alias);
	}

	private static SessionAttributes getSessionAttributes(String branch_) {
		if (sessionAttributes == null) {
			sessionAttributes = new SessionAttributes();
			sessionAttributes.setUsername(un);
			sessionAttributes.setPassword(pw);
			sessionAttributes.setSchema(alias);
		}
		sessionAttributes.setBranch(branch_);
		return sessionAttributes;

	}

	public void handle(ClientJ client) throws Exception {
		saveFounders(client);
		// Если это не клиент без открытия депозитного счета
		if (client.getJ_sign_dep_acc().equalsIgnoreCase(ClientUtil.CHECKBOX_N))
			saveDirectorAndAccountant(client);
	}

	private void saveDirectorAndAccountant(ClientJ client) throws Exception {
		// Dereference director and accountant as accountant points to director
		Person clonedAccountant = (Person) client.getAccountant().clone();
		// Person oldAccountant = client.getAccountant();
		// oldAccountant = null;
		client.setAccountant(null);
		client.setAccountant(clonedAccountant);

		// Если директор и бухгалтер одно и тоже лицо, создаем под одним id

		PersonMapService personMapService = PersonMapService.instance(alias);
		Person director = client.getDirector();
		director.setBranch(client.getBranch());
		director.setName(director.personName());
		//logger.error("Director " + director);
		Res directorResult = personMapService.personAction(director, PersonMapService.ACTION_CREATE);
		if (directorResult.getCode() != 0) {
			throw new Exception(directorResult.getName());
		}
		PersonMap directorMap = PersonMap.directorInstance(client.getBranch());
		directorMap.setPerson_id(directorResult.getName());
		directorMap.setPrson_name(director.getName());
		directorMap.setPerson(director);
		directorMap.setClient_id(client.getId_client());
		directorResult = personMapService.relationAction(directorMap, PersonMapService.ACTION_CREATE);
		if (directorResult.getCode() != 0) {
			throw new Exception(directorResult.getName());
		}

		Person accountant = client.getAccountant();
		accountant.setName(accountant.personName());
		accountant.setBranch(client.getBranch());
		//logger.error("Accountant " + accountant);
		Res accountantResult = personMapService.personAction(accountant, PersonMapService.ACTION_CREATE);
		if (accountantResult.getCode() != 0) {
			throw new Exception(accountantResult.getName());
		}
		PersonMap accountRelationship = PersonMap.accountantInstance(client.getBranch());
		accountRelationship.setPerson_id(accountantResult.getName());
		accountRelationship.setPrson_name(accountant.getName());
		accountRelationship.setPerson(accountant);
		accountRelationship.setClient_id(client.getId_client());
		accountantResult = personMapService.relationAction(accountRelationship, PersonMapService.ACTION_CREATE);
		if (accountantResult.getCode() != 0) {
			throw new Exception(accountantResult.getName());
		}
	}

	private void saveFounders(ClientJ client) {
		PersonMapService personMapService = PersonMapService.instance(alias);
		List<PersonMap> founders = EbpService.getFoundersFromIndividual(client);
		Res res = null;
		for (PersonMap map : founders) {
			map.setBranch(client.getBranch());
			if (map.getPerson_type().equals(PersonMapUtil.PERSONTYPE_P)) {
				map.getPerson().setBranch(client.getBranch());
				res = personMapService.personAction(map.getPerson(), PersonMapService.ACTION_CREATE);
			} else {
				map.getLegalEntity().setBranch(client.getBranch());
				res = personMapService.leAction(map.getLegalEntity(), PersonMapService.ACTION_CREATE);
			}
			map.setPerson_id(res.getName());
			map.setClient_id(client.getId_client());
			if (res.getCode() == 0) {
				personMapService.relationAction(map, PersonMapService.ACTION_CREATE);
			} else {
				new DeltaRelation.Builder().creatingRelation(map).message(res.getName())
						.state(DeltaRelation.ACTION_CREATE).build().log();
			}
		}

	}
    // метод confirmDirectorAndAccountant не доработан еше. не так работает.
	public void confirmDirectorAndAccountant(ClientJ client) throws Exception {
		PersonMapService personMapService = PersonMapService.instance(alias);
		Dao<PersonMap> personMapDao = DaoFactory.instance().getPersonMapDao();
		PersonMap personMap = new PersonMap(client.getId_client(), client.getBranch());
		personMapDao.setFilter(personMap);
		List<PersonMap> personMaps = personMapDao.getList();
		
		ContactActionService actionService = ContactActionService.getInstance(getSessionAttributes(client.getBranch()));
		RelationshipActionService relationsService = RelationshipActionService.getInstance(sessionAttributes);
		
		for (PersonMap mapItem : personMaps) {
			if (mapItem.getPerson_type().equals(PersonMapUtil.PERSONTYPE_P)) {
				//Person directorOrAccountant = new Person();
				//directorOrAccountant.setBranch(mapItem.getBranch());
				//directorOrAccountant.setId(mapItem.getPerson_id());
				//directorOrAccountant.setName(mapItem.getPrson_name());
				//logger.error("not err 1");
				Customer directorOrAccountant = actionService.doAction(1, actionService.getCustomer(mapItem.getBranch(), mapItem.getPerson_id()), PersonMapService.ACTION_CONFIRM);
				//logger.error("not err 2 "+directorOrAccountant.toString());
				//Res directorOrAccountantResult = personMapService.personAction(directorOrAccountant,
				//		PersonMapService.ACTION_CONFIRM);
				//if (directorOrAccountantResult.getCode() != 0) {
				//	throw new Exception(directorOrAccountantResult.getName());
				//}
				//Res directorOrAccountantResult = personMapService.relationAction(mapItem, PersonMapService.ACTION_CONFIRM);
				//if (directorOrAccountantResult.getCode() != 0) {
				//	throw new Exception(directorOrAccountantResult.getName());
				//}
				Relationship relationship = new Relationship();
				relationship.setPosition(mapItem.getPerson_kind());
				relationship.setMapId( (int)mapItem.getId());
				relationsService.doAction(directorOrAccountant,
						relationship, 3, PersonMapService.ACTION_CONFIRM);
				//logger.error("not err 3 ");
			}
		}
	}

	
}
