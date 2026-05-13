package com.is.humoOperation;

public class TimeTable {

	private String id;
    private String name;
    private String sign_work_day;
    private String start_time;
    private String end_time;
    private String status;
    
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getSign_work_day() {
		return sign_work_day;
	}
	public String getStart_time() {
		return start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public String getStatus() {
		return status;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSign_work_day(String sign_work_day) {
		this.sign_work_day = sign_work_day;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public void setStatus(String status) {
		this.status = status;
	}

    //ID	Действие	Статус день	Время начала	Время окончания	Статус
    
    

}
