package spring_message.common.entity;

import java.util.List;

public class ResultData<T> {
	private String code;
	private String message;
	private List<T> lists;
	private T obj;
	private long total;

	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<T> getLists() {
		return lists;
	}
	public void setLists(List<T> lists) {
		this.lists = lists;
	}
	public T getObj() {
		return obj;
	}
	public void setObj(T obj) {
		this.obj = obj;
	}
	
	public static <T> ResultData<T>  successMsg(List<T> lists)
	{
		ResultData<T> resultData = new ResultData<T>();
		resultData.setLists(lists);
		resultData.setMessage("操作成功");
		resultData.setCode("200");
		return resultData;
	}
	
	
	public static  <T> ResultData<T>  successMsg(T obj)
	{
		ResultData<T> resultData = new ResultData<T>();
		resultData.setObj(obj);
		resultData.setMessage("操作成功");
		resultData.setCode("200");
		return resultData;
	}
	
	public static  ResultData  successMsg(String msg)
	{
		ResultData resultData = new ResultData();
		resultData.setMessage(msg);
		resultData.setCode("200");
		return resultData;
	}
	
	public static  ResultData  failMsg(String msg)
	{
		ResultData resultData = new ResultData();
		resultData.setMessage(msg);
		resultData.setCode("300");
		return resultData;
	}
}
