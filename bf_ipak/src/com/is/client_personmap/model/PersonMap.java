package com.is.client_personmap.model;

import java.math.BigDecimal;
import java.util.Comparator;

import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;
import com.is.client_personmap.PersonMapUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class PersonMap implements Comparable{
	@Getter @Setter private long id;
    @Getter @Setter private String branch;
    @Getter @Setter private String client_id;
    @Getter @Setter private String person_type;
    @Getter @Setter private String person_id;
    @Getter @Setter private String person_kind;
    @Getter @Setter private String prson_name;
    @Getter @Setter private int sign_role;
    @Getter @Setter private String person_nci_id;
    @Getter private String idSap;
    @Getter @Setter private int state;
    
    @Getter @Setter private String number_tax_registration;
    @Getter @Setter private boolean fromSap;
    @Getter @Setter private boolean isOld;
    @Setter private FounderCapital capital;
    @Setter private Person person;
    @Setter private LegalEntity legalEntity;
    @Getter @Setter private boolean doNotSendRelationRequest;
    @Getter @Setter private String union_id;
    //@Getter @Setter private boolean signCreationBeneficiaryToo;
    
    public PersonMap() {
    	
    }
    public PersonMap(String client_id) {
    	this.client_id = client_id;
    }
    public PersonMap(long id, String branch){
    	this.id = id;
    	this.branch = branch;
    }
    private PersonMap(String branch, String personKind, String PersonType) {
    	this.branch = branch;
    	this.person_kind = personKind;
    	this.person_type = PersonType;
    }

    public PersonMap(String id_client, String branch) {
        this.client_id = id_client;
        this.branch = branch;
    }

    public static PersonMap ipInstance(String branch) {
    	return new PersonMap(branch, PersonMapUtil.PERSONKIND_IP, PersonMapUtil.PERSONTYPE_P);
    }
    public static PersonMap legalFounderInstance(String branch) {
    	return new PersonMap(branch, PersonMapUtil.PERSONKIND_FOUNDER, PersonMapUtil.PERSONTYPE_J);
    }
    public static PersonMap individFounderInstance(String branch) {
    	return new PersonMap(branch, PersonMapUtil.PERSONKIND_FOUNDER, PersonMapUtil.PERSONTYPE_P);
    }
    public static PersonMap benefFounderInstance(String branch) {
    	return new PersonMap(branch, PersonMapUtil.PERSONKIND_BENEFICIARY, PersonMapUtil.PERSONTYPE_P);
    }
    public static PersonMap directorInstance(String branch) {
    	return new PersonMap(branch, PersonMapUtil.PERSONKIND_DIR, PersonMapUtil.PERSONTYPE_P);
    }
    public static PersonMap accountantInstance(String branch) {
    	return new PersonMap(branch, PersonMapUtil.PERSONKIND_ACC, PersonMapUtil.PERSONTYPE_P);
    }
	public PersonMap(long id, String branch, String client_id,
			String person_type, String person_id, String person_kind,
			String prson_name, boolean fromSap, int state) {
		super();
		this.id = id;
		this.branch = branch;
		this.client_id = client_id;
		this.person_type = person_type;
		this.person_id = person_id;
		this.person_kind = person_kind;
		this.prson_name = prson_name;
		this.state = state;
		this.fromSap = fromSap;
	}
	
	public PersonMap(String person_id, String idSap, String branch, String person_type, String person_kind,
			String prson_name, boolean fromSap) {
		super();
		this.person_id = person_id;
		this.idSap = idSap;
		this.branch = branch;
		this.person_type = person_type;
		this.person_kind = person_kind;
		this.prson_name = prson_name;
		this.fromSap = fromSap;
	}
    
	public void setIdSap(String idSap) {
		this.idSap = idSap;
		if(person_type.equals(PersonMapUtil.PERSONTYPE_J)) {
			getLegalEntity().setIdSap(idSap);
		} else if(person_type.equals(PersonMapUtil.PERSONTYPE_P)) {
			getPerson().setIdSap(idSap);;
		}
	}
	
	public Person getPerson() {
		if(person == null) {
			person = new Person();
		}
		return person;
	}
	
	
	
	public LegalEntity getLegalEntity() {
		if(legalEntity == null) {
			legalEntity = new LegalEntity();
		}
		return legalEntity;
	}
	
	public FounderCapital getCapital() {
		if(capital == null) {
			capital = new FounderCapital();
		}
		return capital;
	}
	
	public String name() {
		if(person_type.equals(PersonMapUtil.PERSONTYPE_J)) {
			return getLegalEntity().getName();
		} else if(person_type.equals(PersonMapUtil.PERSONTYPE_P)) {
			return getPerson().personName();
		}
		return null;	
	}
	
	public String address() {
		if(person_type.equals(PersonMapUtil.PERSONTYPE_J)) {
			return getLegalEntity().getPost_address();
		} else if(person_type.equals(PersonMapUtil.PERSONTYPE_P)) {
			return getPerson().getPost_address();
		}
		return null;
	}
	public int founderType() {
		if(person_type.equals(PersonMapUtil.PERSONTYPE_J)) {
			return 2;
		} else if(person_type.equals(PersonMapUtil.PERSONTYPE_P)) {
			return 1;
		}
		return 0;
	}
	public String country() {
		if(person_type.equals(PersonMapUtil.PERSONTYPE_J)) {
			return getLegalEntity().getCode_country();
		} else if(person_type.equals(PersonMapUtil.PERSONTYPE_P)) {
			return getPerson().getCode_citizenship();
		}
		return null;
	}
	
	public int founderState() {
		if(person_type.equals(PersonMapUtil.PERSONTYPE_J)) {
			return getLegalEntity().getState();
		} else {
			return getPerson().getState();
		}
	}
	
	public void person(String kind) {
    	this.person_type = PersonMapUtil.PERSONTYPE_P;
    }

	public <T> void setFounder(T founder) {
		if(person_type.equals(PersonMapUtil.PERSONTYPE_J)) {
			setLegalEntity((LegalEntity) founder);
			getLegalEntity().setBranch(branch);
			setPerson_id(getLegalEntity().getId());
		} else if(person_type.equals(PersonMapUtil.PERSONTYPE_P)) {
			setPerson((Person) founder);
			getPerson().setBranch(branch);
			setPerson_id(getPerson().getId());
		}
	}
	
    public void founder() {
    	this.person_kind = PersonMapUtil.PERSONKIND_FOUNDER;
    	this.person_type = PersonMapUtil.PERSONTYPE_J;
    }

	// 05-03-2018
    public boolean isExceedsPartOfCapital(){
    	if (this.getCapital() != null && this.getCapital().getPart_of_capital() != null)
    		return this.getCapital().getPart_of_capital().compareTo(BigDecimal.TEN) > 0;
    	return false;
	}

    @Override
    public int compareTo(Object o) {
        PersonMap that = (PersonMap) o;
        return ComparisonChain.start()
                .compare(this.person_type,that.person_type)
                .compare(this.prson_name,that.prson_name,Ordering.usingToString())
                .compare(this.person_kind,that.person_kind)
                .compare(this.state,that.state)
                .compare(this.capital, that.capital, new Comparator<FounderCapital>() {
                    @Override
                    public int compare(FounderCapital o1, FounderCapital o2) {
                        if (o1 == null
                                || o2 == null
                                || o1.part_of_capital == null
                                || o2.part_of_capital == null)
                            return 0;
                        return o1.part_of_capital.compareTo(o2.part_of_capital);
                    }
                })
                .result();
    }
//	@Override
//	public boolean equals(Object obj) {
//		if (obj == null) {
//			return false;
//		}
//		PersonMap other = (PersonMap) obj;
//		return this.id == other.getId() && this.branch.equals(other.getBranch());
//	}
}
