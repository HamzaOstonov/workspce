package com.is.delta_relations;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Date;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.base.SqlScripts;
import com.is.base.utils.DbUtils;
import com.is.client_personmap.model.PersonMap;
import com.is.client_sap.abstraction.AbstractSapRelationService;
import com.is.utils.CheckNull;

import lombok.Getter;
import lombok.Setter;

public class DeltaRelation {
	
	@Getter @Setter private long id;
	@Getter @Setter private String branch;
	@Getter @Setter private long idPersonMap;
	@Getter @Setter private int action;
	@Getter @Setter private Date vDate;
	@Getter @Setter private String message;
	@Getter @Setter private int state;
	@Getter @Setter private int user_id;
	
	@Getter @Setter private String parentName;
	@Getter @Setter private String childName;
	@Getter @Setter private String personKind;
	@Getter @Setter private String relationType;
	
	public static int ACTION_CREATE = 1;
	public static int ACTION_DELETE = 2;
	public static int ACTION_MODIFY = 3;
	
	public DeltaRelation() {
	}
	
	public DeltaRelation(long id, String branch, long idPersonMap, int action, Date vDate, String message, int state,
			int user_id, String parentName, String childName, String personKind) {
		super();
		this.id = id;
		this.branch = branch;
		this.idPersonMap = idPersonMap;
		this.action = action;
		this.vDate = vDate;
		this.message = message;
		this.state = state;
		this.user_id = user_id;
		this.parentName = parentName;
		this.childName = childName;
		this.personKind = personKind;
	}
	
	public DeltaRelation(Builder builder) {
		this.action = builder.getAction();
		this.idPersonMap = builder.getIdPersonMap();
		this.branch = builder.getBranch();
		this.message = builder.getMessage();
		this.state = builder.getState();
	}
	
	public void log() {
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		CallableStatement init = null;
		try {
			c = ConnectionPool.getConnection();
			init = c.prepareCall(SqlScripts.INFO_INIT.getSql());
			init.execute();
			ps = c.prepareStatement("select seq_delta_relations.nextval from dual");
			rs = ps.executeQuery();
			if(rs.next()) {
				setId(rs.getLong(1));
			}
			ps.close();
			ps = c.prepareStatement("insert into delta_relations(id, branch, action,id_person_map, message,state, v_date) values(?,?,?,?,?,1,?)");
			ps.setLong(1, getId());
			ps.setString(2, getBranch());
			ps.setInt(3, getAction());
			ps.setLong(4, getIdPersonMap());
			ps.setString(5, getMessage());
			ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
			ps.executeUpdate();
			c.commit();
			
		} catch(Exception e) {
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			DbUtils.closeStmt(ps);
			ConnectionPool.close(c);
		}
	}
	
	public static class Builder {
		@Getter private String branch;
		@Getter private long idPersonMap;
		@Getter private int action;
		@Getter private String message;
		@Getter private int state;
		
		public Builder() {}
		public Builder(PersonMap map) {
			this.idPersonMap = map.getId();
			this.branch = map.getBranch();
		}
		
		public Builder creatingRelation(PersonMap map) {
			this.idPersonMap = map.getId();
			this.branch = map.getBranch();
			this.action = ACTION_CREATE;
			return this;
		}
		public Builder action(int action) {
			this.action = action;
			return this;
		}
		
		public Builder action(String action) {
			if (action.equals(AbstractSapRelationService.REL_MODE_CREATE)) {
				this.action = ACTION_CREATE;
			} else if (action.equals(AbstractSapRelationService.REL_MODE_DELETE)) {
				this.action = ACTION_DELETE;
			} else if (action.equals(AbstractSapRelationService.REL_MODE_MODIFY)) {
				this.action = ACTION_MODIFY;
			}
			
			return this;
		}
		
		public Builder deletingRelation(PersonMap map) {
			this.idPersonMap = map.getId();
			this.branch = map.getBranch();
			this.action = ACTION_DELETE;
			return this;
		}
		
		public Builder modifyingRelation(PersonMap map) {
			this.idPersonMap = map.getId();
			this.branch = map.getBranch();
			this.action = ACTION_MODIFY;
			return this;
		}
		
		public Builder message(String mesage) {
			this.message = mesage;
			return this;
		}
		public Builder state(int state) {
			this.state = state;
			return this;
		}
		public DeltaRelation build() {
			return new DeltaRelation(this);
		}
	}
}
