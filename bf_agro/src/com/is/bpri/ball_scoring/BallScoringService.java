package com.is.bpri.ball_scoring;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.is.ConnectionPool;
import com.is.ISLogger;
import com.is.bpri.main_scoring.MainScoringService;
import com.is.bpri.utils.Utils;
import com.is.utils.CheckNull;
import com.is.utils.FilterField;
import com.is.utils.RefData;
import com.is.utils.Res;

public class BallScoringService {
	
	private static String psql1 = "select t.* from(select t.*,rownum rwnm from (select * from ( ";
	private static String psql2 = " ) s ) t where rownum <= ?) t  where t.rwnm >= ? ";
	private static BallScoringService instance;
	
	private BallScoringService() {}
	
	public static BallScoringService getInstance() {
        if (instance == null) {
             instance = new BallScoringService();
        }
        return instance;
    }
	
	protected static List<BallScoringModel> getModel(BallScoringModel ball,Integer itemStartNumber,Integer pageSize,String alias){
		List<BallScoringModel> list = new ArrayList<BallScoringModel>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			list = getQuestion(ball,itemStartNumber,pageSize,alias, null, c);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	protected static List<ResultScoringModel> getModel(ResultScoringModel result,Integer itemStartNumber,Integer pageSize,String alias){
		List<ResultScoringModel> list = new ArrayList<ResultScoringModel>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			list = getResults(result,itemStartNumber,pageSize,alias, null, c);
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	private static String getCond(List<FilterField> flfields) {
		return " and ";
	}
	
	private static List<FilterField> getFilterFields(BallScoringModel ball){
		List<FilterField> flfields = new ArrayList<FilterField>();
		if(ball.getQ_order()!=null&&ball.getQ_order()>0){
			flfields.add(new FilterField(getCond(flfields)+" q_id = ?",ball.getQ_id()));
		}
		if(ball.getBpr_id()!=null&&ball.getBpr_id()>0){
			flfields.add(new FilterField(getCond(flfields)+" bpr_id = ?",ball.getBpr_id()));
		}
		return flfields;
	}
	
	private static List<FilterField> getFilterFieldsResult(ResultScoringModel result){
		List<FilterField> flfields = new ArrayList<FilterField>();
		if(result.getBpr_id()>0){
			flfields.add(new FilterField(" where "+" bpr_id = ?",result.getBpr_id()));
		}
		return flfields;
	}
	
	private static List<BallScoringModel> getQuestion(BallScoringModel ball,Integer itemStartNumber,Integer pageSize,String alias,Long a_id,Connection c) throws Exception{
		List<BallScoringModel> list = new ArrayList<BallScoringModel>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String temp = a_id==null?" is null ":" = "+a_id;
		StringBuffer buff = new StringBuffer();
		int params;
		int v_lowerbound = itemStartNumber + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		try {
			List<FilterField> flFields = getFilterFields(ball);
			if (flFields.size() > 0) {
				for (int i = 0; i < flFields.size(); i++) {
					buff.append(flFields.get(i).getSqlwhere());
				}
			}
			ps = c.prepareStatement(psql1+"select * from bpr_question_scoring q where q.a_id "+temp+" "+buff+" order by q.q_order"+psql2);
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			rs = ps.executeQuery();
			while(rs.next()){
				List<AnswerModel> answers = getAnswers(ball,itemStartNumber,pageSize,alias,rs.getLong("q_id"), c);
				list.add(new BallScoringModel(
						rs.getInt("bpr_id"),
						rs.getLong("q_id"),
						rs.getString("q_name"),
						rs.getLong("q_order"),
						rs.getLong("a_id"),
						answers
				));
			}
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
		return list;
	}
	
	private static List<ResultScoringModel> getResults(ResultScoringModel result,Integer itemStartNumber,Integer pageSize,String alias,Long a_id,Connection c) throws Exception{
		List<ResultScoringModel> list = new ArrayList<ResultScoringModel>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer buff = new StringBuffer();
		int params;
		int v_lowerbound = itemStartNumber + 1;
		int v_upperbound = v_lowerbound + pageSize - 1;
		try {
			List<FilterField> flFields = getFilterFieldsResult(result);
			if (flFields.size() > 0) {
				for (int i = 0; i < flFields.size(); i++) {
					buff.append(flFields.get(i).getSqlwhere());
				}
			}
			ps = c.prepareStatement(psql1+"select * from (select * from bpr_ball_scoring_result order by interval_from) "+buff+""+psql2);
			for (params = 0; params < flFields.size(); params++) {
				ps.setObject(params + 1, flFields.get(params).getColobject());
			}
			params++;
			ps.setInt(params++, v_upperbound);
			ps.setInt(params++, v_lowerbound);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new ResultScoringModel(
						rs.getInt("bpr_id"),
						rs.getLong("interval_from"),
						rs.getLong("interval_before"),
						rs.getString("name_result"),
						rs.getInt("is_close_credit")
				));
			}
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
		return list;
	}
	
	protected static List<ResultScoringModel> getResults(int bpr_id){
		List<ResultScoringModel> list = new ArrayList<ResultScoringModel>();
		Connection c = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select * from bpr_ball_scoring_result where bpr_id = ?");
			ps.setInt(1, bpr_id);
			rs = ps.executeQuery();
			while(rs.next()){
				list.add(new ResultScoringModel(
						rs.getInt("bpr_id"),
						rs.getLong("interval_from"),
						rs.getLong("interval_before"),
						rs.getString("name_result"),
						rs.getInt("is_close_credit")
				));
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return list;
	}
	
	protected static int getCount(BallScoringModel ball, String alias){
		PreparedStatement ps = null;
		Connection c = null;
		ResultSet rs = null;
		StringBuffer buff = new StringBuffer();
		int cnt = 0;
		try {
			List<FilterField> flFields = getFilterFields(ball);
			if (flFields.size() > 0) {
				for (int i = 0; i < flFields.size(); i++) {
					buff.append(flFields.get(i).getSqlwhere());
				}
			}
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select count(*) from bpr_question_scoring q where q.a_id is null "+buff);
			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			rs = ps.executeQuery();
			if(rs.next()){
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return cnt;
	}
	
	protected static int getCountResult(ResultScoringModel result, String alias){
		PreparedStatement ps = null;
		Connection c = null;
		ResultSet rs = null;
		StringBuffer buff = new StringBuffer();
		int cnt = 0;
		try {
			List<FilterField> flFields = getFilterFieldsResult(result);
			if (flFields.size() > 0) {
				for (int i = 0; i < flFields.size(); i++) {
					buff.append(flFields.get(i).getSqlwhere());
				}
			}
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("select count(*) from bpr_ball_scoring_result "+buff);
			for (int k = 0; k < flFields.size(); k++) {
				ps.setObject(k + 1, flFields.get(k).getColobject());
			}
			rs = ps.executeQuery();
			if(rs.next()){
				cnt = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(rs);
			Utils.close(ps);
			ConnectionPool.close(c);
		}
		return cnt;
	}
	
	private static List<AnswerModel> getAnswers(BallScoringModel ball,Integer itemStartNumber,Integer pageSize,String alias,Long q_id,Connection c) throws Exception{
		List<AnswerModel> list = new ArrayList<AnswerModel>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement("select * from BPR_answer_scoring where q_id="+q_id);
			rs = ps.executeQuery();
			while(rs.next()){
				List<BallScoringModel> questions = getQuestion(ball,itemStartNumber,pageSize,alias, rs.getLong("a_id"), c);
				list.add(new AnswerModel(
						rs.getLong("q_id"),
						rs.getLong("a_id"),
						rs.getString("a_name"),
						rs.getLong("a_ball"),
						rs.getInt("type_answer"),
						questions
				));
			}
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
		return list;
	}

	protected static List<RefData> getQuestionType(String alias){
		return Utils.getRefData("select * from ss_bpr_question_scoring_types", alias);
	}
	
	protected static List<RefData> getResultAnswer(String alias){
		return Utils.getRefData("select * from ss_bpr_is_close_credit", alias);
	}
	
	protected static String getIsCloseAnswer(int isClose){
		return Utils.getData("select name from ss_bpr_is_close_credit where id = "+isClose, "");
	}
	
	protected static void deleteScoringQuestion(BallScoringModel ball,Res res){
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			deleteBallForm(ball, c);
			c.commit();
		} catch (Exception e) {
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			ISLogger.getLogger().error(CheckNull.getPstr(e));
			Utils.rollback(c);
			e.printStackTrace();
		} finally {
			ConnectionPool.close(c);
		}
	}
	
	private static void deleteBallForm(BallScoringModel ball,Connection c) throws Exception{
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("delete from bpr_question_scoring where bpr_id = ? and q_id = ?");
			ps.setInt(1, ball.getBpr_id());
			ps.setLong(2, ball.getQ_id());
			ps.execute();
			if(ball.getAnswers()!=null){
				for (int i = 0; i < ball.getAnswers().size(); i++) {
					deleteBallAnswers(ball.getAnswers().get(i), c);
				}
			}
		} finally {
			Utils.close(ps);
		}
	}
	
	private static void deleteBallAnswers(AnswerModel answer,Connection c) throws Exception{
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("delete from bpr_answer_scoring where a_id = ? and q_id = ?");
			ps.setLong(1, answer.getA_id());
			ps.setLong(2, answer.getQ_id());
			ps.execute();
			if(answer.getBallScoringModels()!=null){
				for (int i = 0; i < answer.getBallScoringModels().size(); i++) {
					deleteBallForm(answer.getBallScoringModels().get(i), c);
				}
			}
		} finally {
			Utils.close(ps);
		}
	}
	
	protected static void updateScoringForm(BallScoringModel ball,Res res){
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			updateBallForm(ball, c);
			c.commit();
		} catch (Exception e) {
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
			Utils.rollback(c);
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
	}
	
	private static void updateBallForm(BallScoringModel ball,Connection c) throws Exception{
		PreparedStatement ps = null;
		try {
			MainScoringService.updateScoringType(c, ball.getBpr_id(), 2);
			if(ball.isDeleted()){
				deleteBallForm(ball, c);
			} else if(ball.isNew()){
				insertBallForm(ball, c);
			}else {
				ps = c.prepareStatement("update bpr_question_scoring set q_name = ?, q_order = ? where bpr_id = ? and q_id = ?");
				ps.setString(1, ball.getQuestion_name());
				ps.setLong(2, ball.getQ_order());
				ps.setInt(3, ball.getBpr_id());
				ps.setLong(4, ball.getQ_id());
				ps.execute();
				if(ball.getAnswers()!=null){
					for (int i = 0; i < ball.getAnswers().size(); i++) {
						updateBallAnswers(ball.getAnswers().get(i), c);
					}
				}
			}
		} finally {
			Utils.close(ps);
		}
	}
	
	private static void updateBallAnswers(AnswerModel answer,Connection c) throws Exception{
		PreparedStatement ps = null;
		try {
			if(answer.isDeleted()){
				deleteBallAnswers(answer, c);
			} else if(answer.isNew()){
				insertBallAnswers(answer, c);
			} else {
				ps = c.prepareStatement("update bpr_answer_scoring set a_name = ?, a_ball = ?, type_answer = ? where a_id = ? and q_id = ?");
				ps.setString(1, answer.getA_name());
				ps.setLong(2, answer.getType_answer()==2?0:answer.getA_ball());
				ps.setInt(3, answer.getType_answer());
				ps.setLong(4,answer.getA_id());
				ps.setLong(5, answer.getQ_id());
				ps.execute();
				if(answer.getBallScoringModels()!=null){
					for (int i = 0; i < answer.getBallScoringModels().size(); i++) {
						updateBallForm(answer.getBallScoringModels().get(i), c);
					}
				}
			}
		} finally {
			Utils.close(ps);
		}
	}
	
	protected static void insertScoringForm(BallScoringModel ball,Res res){
		Connection c = null;
		try {
			c = ConnectionPool.getConnection();
			MainScoringService.insertScoringType(c, ball.getBpr_id(), 2);
			insertBallForm(ball, c);
			c.commit();
		} catch (Exception e) {
			res.setCode(1);
			res.setName(CheckNull.getPstr(e));
			e.printStackTrace();
			Utils.rollback(c);
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			ConnectionPool.close(c);
		}
	}
	
	private static void insertBallForm(BallScoringModel ball,Connection c) throws Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement("select SEQ_bpr_question_scoring.nextval from dual");
			rs = ps.executeQuery();
			if(rs.next()){
				ball.setQ_id(rs.getLong(1));
			}
			ps = c.prepareStatement("insert into bpr_question_scoring (bpr_id,q_id,q_name,q_order,a_id) values (?,?,?,?,?)");
			System.out.println("ball.getBpr_id() = "+ball.getBpr_id());
			ps.setInt(1, ball.getBpr_id());
			System.out.println("ball.getQ_id() = "+ball.getQ_id());
			ps.setLong(2, ball.getQ_id());
			System.out.println("ball.getQuestion_name() = "+ball.getQuestion_name());
			ps.setString(3, ball.getQuestion_name());
			System.out.println("ball.getQ_order() = "+ball.getQ_order());
			ps.setLong(4, ball.getQ_order());
			System.out.println("ball.getMap_id() = "+ball.getMap_id());
			ps.setString(5, ball.getMap_id()==null||ball.getMap_id()<1?null:ball.getMap_id()+"");
			ps.execute();
			if(ball.getAnswers()!=null){
				for (int i = 0; i < ball.getAnswers().size(); i++) {
					AnswerModel answer = ball.getAnswers().get(i);
					if(!answer.isDeleted()){
						answer.setQ_id(ball.getQ_id());
						insertBallAnswers(answer, c);
					}
				}
			}
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
	}
	
	private static void insertBallAnswers(AnswerModel answer,Connection c) throws Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = c.prepareStatement("select SEQ_BPR_answer_scoring.nextval from dual");
			rs = ps.executeQuery();
			if(rs.next()) answer.setA_id(rs.getLong(1));
			ps = c.prepareStatement("insert into BPR_answer_scoring (q_id,a_id,a_name,a_ball,type_answer) values (?,?,?,?,?)");
			ps.setLong(1, answer.getQ_id());
			ps.setLong(2, answer.getA_id());
			ps.setString(3, answer.getA_name());
			ps.setLong(4, answer.getA_ball()==null?0L:answer.getA_ball());
			ps.setInt(5, answer.getType_answer());
			ps.execute();
			if(answer.getBallScoringModels()!=null){
				for (int i = 0; i < answer.getBallScoringModels().size(); i++) {
					BallScoringModel ball = answer.getBallScoringModels().get(i);
					if(!ball.isDeleted()){
						ball.setMap_id(answer.getA_id());
						insertBallForm(ball, c);
					}
				}
			}
		} finally {
			Utils.close(rs);
			Utils.close(ps);
		}
	}
	
	protected static void insertResults(List<ResultScoringModel> list,int bpr_id,Res res){
		Connection c = null;
		PreparedStatement ps = null;
		Long before = 0L;
		try {
			c = ConnectionPool.getConnection();
			deleteAllResults(bpr_id, c);
			Collections.sort(list,getInstance().new SortByIntervalFrom());
			ps = c.prepareStatement("insert into bpr_ball_scoring_result (bpr_id,interval_from,interval_before,name_result,is_close_credit) values (?,?,?,?,?)");
			for (int i = 0; i < list.size(); i++) {
				if(before!=0) 
					if((before+1)!=list.get(i).getInterval_from()) {
						res.setName("Значение "+(before+1)+" выпало из интервала");
						throw new Exception("Значение "+(before+1)+" выпало из интервала");
					}
				for (int j = 0; j < list.size(); j++) {
					if(list.get(i) != list.get(j)){
						if((list.get(j).getInterval_from()>=list.get(i).getInterval_from()&&list.get(j).getInterval_from()<=list.get(i).getInterval_before())
								||(list.get(j).getInterval_before()>=list.get(i).getInterval_from()&&list.get(j).getInterval_before()<=list.get(i).getInterval_before())){
							res.setName("Некоректный интервал");
							throw new Exception("Некоректный интервал");
						}
					}
				}
				before = list.get(i).getInterval_before();
				ps.setInt(1, bpr_id);
				ps.setLong(2, list.get(i).getInterval_from());
				ps.setLong(3, list.get(i).getInterval_before());
				ps.setString(4, list.get(i).getName_result());
				ps.setInt(5, list.get(i).getIs_close_credit());
				ps.execute();
				System.out.println("--------------");
			}
			c.commit();
		} catch (Exception e) {
			res.setCode(1);
			if(res.getName()==null)res.setName(CheckNull.getPstr(e));
			Utils.rollback(c);
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	private static void deleteAllResults(int bpr_id,Connection c) throws Exception{
		PreparedStatement ps = null;
		try {
			ps = c.prepareStatement("delete from bpr_ball_scoring_result where bpr_id = ?");
			ps.setInt(1, bpr_id);
			ps.execute();
		} finally {
			Utils.close(ps);
		}
	}
	
	protected static void deleteResult(ResultScoringModel result){
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = ConnectionPool.getConnection();
			ps = c.prepareStatement("delete from bpr_ball_scoring_result where bpr_id = ? and interval_from = ? and interval_before = ?");
			ps.setInt(1, result.getBpr_id());
			ps.setLong(2, result.getInterval_from());
			ps.setLong(3, result.getInterval_before());
			ps.execute();
			c.commit();
		} catch (Exception e) {
			Utils.rollback(c);
			e.printStackTrace();
			ISLogger.getLogger().error(CheckNull.getPstr(e));
		} finally {
			Utils.close(ps);
			ConnectionPool.close(c);
		}
	}
	
	private class SortByIntervalFrom implements Comparator<ResultScoringModel> {
		
		@Override
		public int compare(ResultScoringModel o1, ResultScoringModel o2) {
			return o1.getInterval_from().compareTo(o2.getInterval_from());
		}
		
	}
}
