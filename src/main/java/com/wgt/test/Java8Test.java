package com.wgt.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

import org.junit.Test;

public class Java8Test {
	public static void main(String[] args) {
		
	}
	
	/**
	 * Optional.of()或者Optional.ofNullable()：创建Optional对象，差别在于of不允许参数是null，而ofNullable则无限制。
	 * @date :2018年6月26日
	 */
	@Test
	public void fun0() {
		try {
			// 参数不能是null  
			Optional<Integer> optional1 = Optional.of(1);  
			// 参数可以是null  
			Optional<Integer> optional2 = Optional.ofNullable(null);  
			// 参数可以是非null  
			Optional<Integer> optional3 = Optional.ofNullable(2);  
			System.out.println(optional1);
			System.out.println(optional2);
			System.out.println(optional3);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Optional.empty()：所有null包装成的Optional对象：
	 * @date :2018年6月26日
	 */
	@Test
	public void fun1() {
		Optional<Integer> optional1 = Optional.ofNullable(null);  
		Optional<Integer> optional2 = Optional.ofNullable(null);  
		System.out.println(optional1 == optional2);// true  
		System.out.println(optional1 == Optional.<Integer>empty());// true  
		  
		Object o1 = Optional.<Integer>empty();  
		Object o2 = Optional.<String>empty();  
		System.out.println(o1 == o2);// true  
	}
	/**
	 * isPresent()：判断值是否存在
	 * @date :2018年6月26日
	 */
	@Test
	public void fun2() {
		Optional<Integer> optional1 = Optional.ofNullable(1);  
		Optional<Integer> optional2 = Optional.ofNullable(null);  
		  
		// isPresent判断值是否存在  
		System.out.println(optional1.isPresent() == true);  
		System.out.println(optional2.isPresent() == false);  
	}
	/**
	 * ifPresent(Consumer consumer)：如果option对象保存的值不是null，则调用consumer对象，否则不调用
	 * @date :2018年6月26日
	 */
	@Test
	public void fun3() {
		Optional<Integer> optional1 = Optional.ofNullable(1);  
		Optional<Integer> optional2 = Optional.ofNullable(null);  
		  
		// 如果不是null,调用Consumer  
		optional1.ifPresent(new Consumer<Integer>() {  
		    @Override  
		    public void accept(Integer t) {  
		        System.out.println("value is " + t);  
		    }  
		});  
		  
		// null,不调用Consumer  
		optional2.ifPresent(new Consumer<Integer>() {  
		    @Override  
		    public void accept(Integer t) {  
		        System.out.println("value is " + t);  
		    }  
		});  
	}
	
	
	/**
	 * orElse(value)：如果optional对象保存的值不是null，则返回原来的值，否则返回value
	 * @date :2018年6月26日
	 */
	@Test
	public void fun4() {
		Optional<Integer> optional1 = Optional.ofNullable(1);  
		Optional<Integer> optional2 = Optional.ofNullable(null);  
		  
		// orElse  
		System.out.println(optional1.orElse(1000) == 1);// true  
		System.out.println(optional2.orElse(1000) == 1000);// true  

	}
	/**
	 * orElseGet(Supplier supplier)：功能与orElse一样，只不过orElseGet参数是一个对象
	 * @date :2018年6月26日
	 */
	@Test 
	public void fun5() {
		Optional<Integer> optional1 = Optional.ofNullable(1);  
		Optional<Integer> optional2 = Optional.ofNullable(null);  
		  
		System.out.println(optional1.orElseGet(() -> {  
		    return 1000;  
		}) == 1);//true  
		  
		System.out.println(optional2.orElseGet(() -> {  
		    return 1000;  
		}) == 1000);//true  

	}
	/**
	 * orElseThrow()：值不存在则抛出异常，存在则什么不做，有点类似Guava的Precoditions
	 * @date :2018年6月26日
	 */
	@Test
	public void fun6() {
		Optional<Integer> optional1 = Optional.ofNullable(1);  
		Optional<Integer> optional2 = Optional.ofNullable(null);  
		  
		optional1.orElseThrow(()->{throw new IllegalStateException();});  
		  
		try  
		{  
		    // 抛出异常  
		    optional2.orElseThrow(()->{throw new IllegalStateException();});  
		}  
		catch(IllegalStateException e )  
		{  
		    e.printStackTrace();  
		}  
	}
	/**
	 * filter(Predicate)：判断Optional对象中保存的值是否满足Predicate，并返回新的Optional。
	 * @date :2018年6月26日
	 */
	@Test
	public void fun7() {
		Optional<Integer> optional1 = Optional.ofNullable(1);  
		Optional<Integer> optional2 = Optional.ofNullable(null);  
		  
		Optional<Integer> filter1 = optional1.filter((a) -> a == null);  
		Optional<Integer> filter2 = optional1.filter((a) -> a == 1);  
		Optional<Integer> filter3 = optional2.filter((a) -> a == null);  
		System.out.println(filter1.isPresent());// false  
		System.out.println(filter2.isPresent());// true  
		System.out.println(filter2.get().intValue() == 1);// true  
		System.out.println(filter3.isPresent());// false  
	}
	/**
	 * map(Function)：对Optional中保存的值进行函数运算，并返回新的Optional(可以是任何类型)
	 * @date :2018年6月26日
	 */
	@Test
	public void fun8() {
		Optional<Integer> optional1 = Optional.ofNullable(1);  
		Optional<Integer> optional2 = Optional.ofNullable(null);  
		  
		Optional<String> str1Optional = optional1.map((a) -> "key" + a);  
		Optional<String> str2Optional = optional2.map((a) -> "key" + a);  
		  
		System.out.println(str1Optional.get());// key1  
		System.out.println(str2Optional.get());// false  
	}
	/**
	 * flatMap()：功能与map()相似，差别请看如下代码。
	 * 	flatMap方法与map方法类似，区别在于mapping函数的返回值不同。
	 * 	map方法的mapping函数返回值可以是任何类型T，而flatMap方法的mapping函数必须是Optional。
	 */
	@Test
	public void fun9() {
		Optional<Integer> optional1 = Optional.ofNullable(1);  
		  
		Optional<Optional<String>> str1Optional = optional1.map((a) -> {  
		    return Optional.<String>of("key" + a);  
		});  
		  
		Optional<String> str2Optional = optional1.flatMap((a) -> {  
		    return Optional.<String>of("key" + a);  
		});  
		  
		System.out.println(str1Optional.get().get());// key1  
		System.out.println(str2Optional.get());// key1  
	}
	public class A{
		private String age;
		private String name;
		private Address address;
		public A() {
			super();
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public Address getAddress() {
			return address;
		}
		public void setAddress(Address address) {
			this.address = address;
		}
		@Override
		public String toString() {
			return "A [age=" + age + ", name=" + name + ", address=" + address + "]";
		}
	}
	public class Address{
		private String location;
		private String lng;
		private String lat;
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		public String getLng() {
			return lng;
		}
		public void setLng(String lng) {
			this.lng = lng;
		}
		public String getLat() {
			return lat;
		}
		public void setLat(String lat) {
			this.lat = lat;
		}
		@Override
		public String toString() {
			return "Address [location=" + location + ", lng=" + lng + ", lat=" + lat + "]";
		}
		public Address() {
			super();
			// TODO Auto-generated constructor stub
		}
	}
	@Test
	public void fun10() {
		try {
			A aa=new A();
			aa.setAge("10");
			aa=null;
			Optional<A> a = Optional.ofNullable(aa);  
			a.ifPresent(System.out::println);
			
			String b=a.map(u->u.getAge()).orElse("23");
			System.out.println(b);
			
			String c=a.map(u->u.getAge()).orElseGet(()->{return "100";});
			System.out.println(c);
			//age.ifPresent(System.out.println(1));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void fun11() {
		
		try {
			List<Map<String,Object>> list=findResultList();
			System.out.println("list："+list);
			Optional<List> opList=Optional.ofNullable(list);
			System.out.println("opList:"+opList);
			
			Object o=opList.map(l->l.get(0));
			System.out.println("o:"+o);
			//Optional<Object> name=Optional.ofNullable(list.get(0));
			//name.ifPresent(System.out::println);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public List<Map<String,Object>> findResultList(){
		List<Map<String,Object>> list=null;
		if( (Math.round(Math.random()*2))%2==0) {
			list=new ArrayList();
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("name2", "wgt");
			list.add(map);
		}
		return list;
	}
	
	public A findA(){
		A a=null;
		int random =(int) Math.round(Math.random()*9);
		random=6;
		if(random%2==0) {
			a=new A();
			a.setAge("10");
			if(random%3==0) {
				Address address=new Address();
				address.setLocation("华南家电研究院");
				a.setAddress(address);
			}
		}
		return a;
	}
	@Test
	public void fun12() {
		try {
		A a=findA();
			System.out.println("a:"+a);
			Optional<A> opA=Optional.ofNullable(a);
			opA.ifPresent(s->System.out.println("opA:"+s));
			//opA.ifPresent(System.out::println);
			Object o=Optional.ofNullable(a)
				.map(u->u.getAddress())
				.map(c->c.getLocation())
				.orElseThrow(()->new Exception("取值错误"));
			System.out.println("o:"+o);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

