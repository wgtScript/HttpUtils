package com.wgt.test;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;

import com.wgt.rsa.MD5;
import com.wgt.rsa.RSAUtil;

import net.sf.json.JSONObject;
/**
 * 模拟 ajax 调用接口工具类。
 * @author DELL
 *
 */
public class HttpUtils {

//	private static String ipPort="http://192.168.0.172";
	private static String ipPort="http://192.168.0.251";
//	private static String ipPort="https://html.bndxqc.com";
	private static String token="";
	private static String api="";
	private static String parameter="";
	private static String jsonParameter="";
	
	private static String userId="";
	public static void main(String[] args) {
		
		
		//生产账号：
		//token=getTokenV3("15113991958","rg2iUxyaQIKEdyp9cbXs4lMg5LIdM4jVx9XZ+ApfK+oTqODlMwQutOe0HIQS1bqvw6KAqC7ofUw5SSQ02p4uSJfIEkEMLVEtIrLXOon7pOawPRDwA4XtQuZspELayejEVrBoGc2TUrmRD/K81lGdP6WXXITnm2oZ3aH5OS7DaL4=");
		token=getTokenV3("15113991958","111111");
		System.out.println("V3token:"+token);
		
		
		//获取旧版     正在行程（最新行程）列表
		api="/api/route/app/v2/employeetriplist";
		jsonParameter="{employeeId: '61205'}";
//		pirntlnJsonMethod(api, jsonParameter);
		
		//获取旧版     过去行程（已完成行程）列表
		api="/api/route/app/v2/employeebeforetriplist";
		jsonParameter="{employeeId: '61205'}";
//		pirntlnJsonMethod(api, jsonParameter);
				
		
		token=getTokenV2("15113991958","111111");
		//System.out.println("token:"+token);
		
		//获取用户ID
		api="/api/system/sysRoleInfo/v1/getUserId";
		//pirntlnSimpleMethod(api,parameter);
//		userId=JSONObject.fromObject(sendPostMethod(api,parameter)).get("userId").toString();
//		System.out.println("userId:"+userId);
		
		//获取用户信息，员工信息（选择企业信息）
		api="/api/system/sysRoleInfo/v1/getUserInfo";
		//pirntlnSimpleMethod(api,parameter);
		
		//获取待审批数量（页面5分钟调用一次）
		api="/api/activiti/pc/v1/queryComApproNum";
		jsonParameter="{userId: '"+userId+"'}";
		//pirntlnJsonMethod(api, jsonParameter);
		
		//获取顶层菜单
		api="/api/system/sysRoleResource/queryUserResource";
		jsonParameter="{levelId: '1',companyId: '2'}";
		//pirntlnJsonMethod(api, jsonParameter);
		
		//点击顶层菜单，获取左侧菜单(data为空....)
		api="/api/system/sysRoleResource/queryUserAllResource";
		jsonParameter="{levelId: '1',companyId: '2',parentId:'25'}";
		//pirntlnJsonMethod(api, jsonParameter);
		
		//点击节点子菜单后触发展开
		api="/api/system/sysRoleResource/queryUserExpandResource?levelId=4&parentId=693&companyId=2&webPath=http://192.168.0.251:82";
		parameter="id=693";//用车管理    点击车油管理并不会触发。
		//pirntlnSimpleMethod(api,parameter);
		
		//立即出发--获取员工上次使用车辆、用车事由
		api="/api/route/car2route/app/v3/getcarlist";
		parameter="employeeId=5555";
		//pirntlnSimpleMethod(api,parameter);
		
		//用车管理
		//公车管理---获取所有公车
		api="/api/route/car2info/v3/getcompanycarlist";
		jsonParameter="{companyInfoId:'2',page:'',rows:''}";
		//pirntlnJsonMethod(api, jsonParameter);
		
		//公车管理--保存公司车辆信息
		api="/api/route/car2info/v3/savecompanycarinfo";
		jsonParameter="{companyInfoId:'2',carNumber:'京C23426',carName:'javaTest',id:'39'}";
		//pirntlnJsonMethod(api, jsonParameter);
		
		//公车管理--获取公司的组织结构。（左侧栏）
		api="/api/system/company/dept/pc/v1/list?companyId=2";
		//pirntlnJsonMethod(api, jsonParameter);
		
		//公车管理-- 获取公司中指定部门的员工。（右侧栏）
		api="/api/system/company/emp/pc/v1/queryEmpListByCompanyId?companyId=2";
		jsonParameter="{deptId:'',page:'',rows:'',empName:'吴'}";
		//pirntlnJsonMethod(api, jsonParameter);

		//公车管理--分配车辆
		api="/api/route/car2info/v3/assigncompanycar";
		jsonParameter="{id:'39',empInfo: '[{\"empId\":\"93181\",\"empName\":\"吴广涛\"},{\"empId\":\"2217\",\"empName\":\"杨晓麟\"}]'}";
		//pirntlnJsonMethod(api, jsonParameter);
		
		//公车管理---删除车辆
		api="/api/route/car2info/v3/deletecompanycar";
		jsonParameter= "{id=40}";
		//pirntlnJsonMethod(api, jsonParameter);
		
		//审批设置--- 流程详情
		api="/api/route/car2approvalset/v3/getapprovaldetail";
		jsonParameter="{companyInfoId: 2}";
		//pirntlnJsonMethod(api, jsonParameter);
		
		//审批设置--- 保存设置
		api="/api/route/car2approvalset/v3/saveapprovalset";
		jsonParameter="{companyInfoId:'2',approvalType:'1',"
				+ "approveEmpInfo: [{\"empId\":\"93181\",\"empName\":\"吴广涛\"}],"
				+ "copyEmpInfo: [{\"empId\":\"11596\",\"empName\":\"乐瑶一\"}],id:'6'}";
		//pirntlnJsonMethod(api, jsonParameter);
		
		//已提交行程---行程详情
		api="/api/route/car2routecommit/v3/getcommitedroutedetail";
		parameter="routeId=19";
		//pirntlnSimpleMethod(api,parameter);
		
		//已提交行程---用车事由
		api="/api/route/apply/v3/getcatelist";
		//pirntlnSimpleMethod(api,parameter);
		
		//已提交行程--- 获取列表
		api="/api/route/car2routecommit/v3/getcommitedlist";
		jsonParameter="{companyId: '2',empName: '',carCause: '上下班' ,startDate: '2018-06-24',endDate:'2018-07-24' ,page: '1',rows: '20'}";
		//pirntlnJsonMethod(api, jsonParameter);
		
		//云办公---审批列表---我已处理---查看详情
		api="/api/route/car2route/app/v3/getroute2flowdetail?processId=1020113";
		parameter="{processId:'1020113'}";
		//pirntlnSimpleMethod(api,parameter);
		//pirntlnJsonMethod(api, jsonParameter);	这种地址，跟参数重复的会有问题。
		
		//云办公---审批列表---我已处理---查看详情--查看轨迹
		api="/api/route/car2route/app/v3/getroutepointsbyindex";
		jsonParameter="{routeId: '181',index: 1}";//由于没提交后台的也能看到轨迹，所以这里的routeId不是已提交的。
		//pirntlnJsonMethod(api, jsonParameter);
		
		
		
		//test   myAPI
		ipPort="http://127.0.0.1:8081";
		api="/last_1/getReadNoteList.do";
		jsonParameter="{teacherName: '',noteName: '',noteSummary:'',chapterNo:'',page:'',rows:''}";
		//pirntlnJsonMethod(api, jsonParameter);
		
	}
	
	/**
	 * 	输出一个键值对的简单方法
	 * @author : wgt
	 * @date :2018年7月25日
	 * @param api
	 * @param param
	 */
	public static void pirntlnSimpleMethod(String api,String param) {
		System.out.println("接口地址："+api);
		System.out.println(FormatJsonUtil.formatJson(sendPostMethod(api,param)));
	}
	
	/**
	 * 	输出简单json参数的方法。
	 * @author : wgt
	 * @date :2018年7月25日
	 * @param api
	 * @param params
	 */
	public static void pirntlnJsonMethod(String api,String params) {
		System.out.println("接口地址："+api);
		System.out.println(FormatJsonUtil.formatJson(sendPostMethodByJson(api,params)));
	}
	
	public static String getTokenV3(String userName,String password) {
		String url = ipPort+"/api/system/serviceuser/basic/app/v3/nolog/loginByPassword";
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.setRequestHeader(new Header("channel","BROWSER"));
		method.setRequestHeader(new Header("Content-type","application/x-www-form-urlencoded; charset=UTF-8"));//!!!!!v3根v2的区别
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFbuyHjN4+18OjRSyzOUli1Ic0"
				+ "tD/ZkfoDeHHTD3S6v/sVnfczpafqz7fNXUFCyO7mJlM58IAHoHGvrjhCXs5E29yS"
				+ "I62Qk5tC9g1ofrDcp9DLS3HsBZVfwqVlgtTPV64IUSMckP9ZPradAoyUY5oGXJhk" + "HP+MAEaHaIYaSlgmiQIDAQAB";
		String date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		//password用公钥加密									//md5加盐加密
		String encryptpub = RSAUtil.encryptRSAPublic(MD5.md5(password+"xqc1254548787244")+";;;qqq"+date+";;;qqq"+password, publicKey);
		//设置请求参数
		String json1="{'username':'"+userName+"','password':'"+encryptpub+"'}";
		method.setRequestBody(formatSimpleJson(json1));
		byte[] responseBody=null;
		String callback="";
		try {
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}
			responseBody = method.getResponseBody();
			callback=new String(responseBody, "utf-8");
			JSONObject json=JSONObject.fromObject(callback);
			String token=(String) JSONObject.fromObject(json.get("data")).get("access_token");
			//System.out.println(token);
			return token;
		}
		catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		}
		catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		}catch(Exception e) {
			String token=callback;
		}
		finally {
			method.releaseConnection();
		}
		return callback;
	}
	
	/**
	 * 	v2版本的登录获取token方法。
	 * @author : wgt
	 * @date :2018年7月30日
	 * @param userName
	 * @param password
	 * @return
	 */
	public static String getTokenV2(String userName,String password) {
		String url = ipPort+"/api/system/serviceuser/basic/app/v2/nolog/loginByPassword";
		HttpClient client = new HttpClient();
		String body="{\"grant_type\":\"password\",\"username\":\""+userName+"\",\"credential\":{\"type\":\"PASSWORD\",\"value\":\""+password+"\"}}";		
		PostMethod method = new PostMethod(url);
		method.setRequestHeader(new Header("channel","BROWSER"));
		method.setRequestHeader(new Header("Content-type","application/json; charset=UTF-8"));//获取token的方式
		method.setRequestBody(body);// addParameters(postData);
		byte[] responseBody=null;
		String callback="";
		try {
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}
			responseBody = method.getResponseBody();
			callback=new String(responseBody, "utf-8");
			JSONObject json=JSONObject.fromObject(callback);
			String token=(String) JSONObject.fromObject(json.get("data")).get("access_token");
			//System.out.println(token);
			return token;
		}
		catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		}
		catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		}catch(Exception e) {
			String token=callback;
		}
		finally {
			method.releaseConnection();
		}
		return callback;
	}
	
	/**
	 * 	
	 * @author : wgt
	 * @date :2018年7月25日
	 * @param url	去掉ip端口的url
	 * @param param  键值之间用 = 相连，用 冒号不行
	 * @return
	 */
	public static String  sendPostMethod(String url,String param) {
		url=ipPort+url;
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		method.setRequestHeader(new Header("channel","BROWSER"));//获取用户信息时会判断这个
		method.setRequestHeader(new Header("Content-type","application/x-www-form-urlencoded; charset=utf-8"));//获取接口的方式。
		method.setRequestHeader(new Header("access_token",token));
//		System.out.println("token:"+token);
		method.setRequestBody(param);// addParameters(postData);	      
		String callback="";
		try {
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}
			byte[] responseBody = method.getResponseBody();
			callback=new String(responseBody, "utf-8");
		}
		catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		}
		catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			method.releaseConnection();
		}

		return callback;
	}
	
	/**
	 * 
	 * @author : wgt
	 * @date :2018年7月25日
	 * @param url	去掉ip端口的url
	 * @param json 键值格式可以 ： = 连接,分割键值对 可以 ; ,  分格   开头 加{   结尾加}
	 * @return
	 */
	public static String  sendPostMethodByJson(String url,String json) {
		url=ipPort+url;
		HttpClient client = new HttpClient();
		PostMethod method = new PostMethod(url);
		//method.setRequestHeader(new Header("Content-type","application/x-www-form-urlencoded"));//获取接口的方式。
		method.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");//不添加，中文会乱码
		method.setRequestHeader(new Header("channel","BROWSER"));//获取用户信息时会判断这个
		method.setRequestHeader(new Header("access_token",token));
//		System.out.println("token:"+token);
		if(StringUtils.isNotBlank(json)) {
			method.setRequestBody(formatSimpleJson(json));
		}
		String callback="";
		try {
			int statusCode = client.executeMethod(method);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}
			byte[] responseBody = method.getResponseBody();
			callback=new String(responseBody, "utf-8");
		}
		catch (HttpException e) {
			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		}
		catch (IOException e) {
			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			method.releaseConnection();
		}
		return callback;
	}
	
	/**
	 * 将json参数转成NameValuePair数组
	 * @author : wgt
	 * @date :2018年7月30日
	 * @param json
	 * @return
	 */
	public static NameValuePair[] formatSimpleJson(String json) {
		Map<String,Object> map=JSONObject.fromObject(json);
		ArrayList<NameValuePair> list=new ArrayList<NameValuePair>();
		System.out.println("json串带的参数：");
		for(String key:map.keySet()) {
			System.out.println('\t'+key+"="+map.get(key));
			list.add(new NameValuePair(key, map.get(key).toString()));
		}
		NameValuePair[] data=new NameValuePair[list.size()];
		list.toArray(data);
		return data;
	}
	
	
	
	
	
	
	
}
