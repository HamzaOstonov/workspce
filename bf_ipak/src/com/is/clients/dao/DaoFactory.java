package com.is.clients.dao;

import com.is.base.Dao;
import com.is.client_personmap.dao.FounderCapitalDao;
import com.is.client_personmap.dao.FounderMapDao;
import com.is.client_personmap.dao.LegalEntityDao;
import com.is.client_personmap.dao.PersonDao;
import com.is.client_personmap.dao.PersonMapDao;
import com.is.client_personmap.model.FounderCapital;
import com.is.client_personmap.model.FounderMap;
import com.is.client_personmap.model.LegalEntity;
import com.is.client_personmap.model.Person;
import com.is.client_personmap.model.PersonMap;
import com.is.client_spec.SpecClt;
import com.is.client_spec.SpecCltDao;
import com.is.clients.models.ClientJ;
import com.is.clients.models.SapIpClient;
import com.is.delta_relations.DeltaRelation;
import com.is.delta_relations.DeltaRelationDao;
import com.is.nibbd.models.Nibbd;
import com.is.nibbd.service.NibbdDao;

import lombok.Getter;

public class DaoFactory {
	private String alias;
	
	private DaoFactory() {}
	private DaoFactory(String alias) {
		this.alias = alias;
	}
	
	public static DaoFactory instance() {return new DaoFactory();}
	
	public static DaoFactory getInstance(String alias) {
		return new DaoFactory(alias);
	}
	
	@Getter(lazy=true) private final Dao<ClientJ> clientDao = ClientDao.getInstance(alias);
	@Getter(lazy=true) private final Dao<LegalEntity> legalEntityDao = LegalEntityDao.getInstance(alias);
	@Getter(lazy=true) private final Dao<Person> personDao = PersonDao.getInStance(alias);
	@Getter(lazy=true) private final Dao<SpecClt> specharDao = SpecCltDao.getInstance(alias);
	@Getter(lazy=true) private final Dao<PersonMap> personMapDao = PersonMapDao.getInstance(alias, this);
	@Getter(lazy=true) private final Dao<FounderCapital> capitalDao = FounderCapitalDao.instance(alias);
	@Getter(lazy=true) private final Dao<FounderMap> founderMapDao = FounderMapDao.instance(alias);
	@Getter(lazy=true) private final Dao<Nibbd> nibbdDao = NibbdDao.getInstance(alias, null);
	@Getter(lazy=true) private final Dao<DeltaRelation> deltaRelationDao = DeltaRelationDao.instance(alias);
	@Getter(lazy=true) private final Dao<SapIpClient> sapIpClientDao = alias == null ? SapIpClientDao.instance() : SapIpClientDao.instance(alias);
}
