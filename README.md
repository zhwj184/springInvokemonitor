使用telnet连接到基于spring的应用上执行容器中的bean的任意方法

使用telnet连接到基于spring的应用上执行容器中配置的任何bean的任意方法，可以用来诊断某个方法是否执行有问题，响应时间多少，在生产环境中可以很好的定位及监控方法是否存在问题。

代码在：https://github.com/zhwj184/springInvokemonitor

	git clone git@github.com:zhwj184/springInvokemonitor.git 

	maven clean install

pom依赖：

	<dependency>
		<groupId>org.zhwj184</groupId>
		<artifactId>springinvokemonitor</artifactId>
		<version>1.0-SNAPSHOT</version>
	</dependency>
	
使用方式，在spring的配置文件中添加下面这个bean即可。

	<bean id="ServiceInvokeClient" class="org.zhwj184.ServiceInvokeClient" />
	
使用示例：先写一个service TestBean，里面有两个方法，add和addBean

	import com.alibaba.fastjson.JSON;

	public class TestBean {

		public float add(int a, float b){
			return a + b;
		}
		
		public A addBean(A a, A b){
			A c = new A();
			c.setC(a.getC() + b.getC());
			c.setD(a.getD() + b.getD());
			return c;
		}
		public static void main(String[] args) {
			A c = new A();
			c.setC(23);
			c.setD(323.34);
			System.out.println(JSON.toJSON(c));
		}
		
	}

	class A{
		int c ;
		double d;
		public int getC() {
			return c;
		}
		public void setC(int c) {
			this.c = c;
		}
		public double getD() {
			return d;
		}
		public void setD(double d) {
			this.d = d;
		}
		
	}
	
然后在spring配置文件 service.xml添加

	<bean id="testBean" class="org.zhwj184.TestBean" />
	<bean id="ServiceInvokeClient" class="org.zhwj184.ServiceInvokeClient" />
	
写一个测试类，执行这个main方法，

	import org.springframework.context.support.ClassPathXmlApplicationContext;

	public class MainTest {

		/**
		 * @param args
		 * @throws InterruptedException 
		 */
		public static void main(String[] args) throws InterruptedException {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:service.xml");
			Thread.sleep(100000000);
		}

	}

然后通过telnet连到这个服务上，打开命令行窗口，输入telnet localhost 12667，连上之后，输入ls bean名称，即可查询这个bean的所有方法，使用invoke beanname.method(param1,param2) 执行某个方法，参数如果为bean则可以使用json格式传入，参数之间用;分隔


	======================================================

	   Welcome to Telnet Server: Version 1.0

	======================================================

	List of possible commands:

	Status: displays the status of the server
	cd : [ cd /usr/local]
	pwd: displays the working directory
	ls: displays list of files in the working directory
	mkdir : [ mkdir /usr/local/tmp]
	exit : quit this programme

	ls testBean
	org.zhwj184.A org.zhwj184.TestBean.addBean(org.zhwj184.A,org.zhwj184.A)
	void org.zhwj184.TestBean.main([Ljava.lang.String;)
	float org.zhwj184.TestBean.add(int,float)
	int org.zhwj184.TestBean.hashCode()
	java.lang.Class org.zhwj184.TestBean.getClass()
	void org.zhwj184.TestBean.wait(long,int)
	void org.zhwj184.TestBean.wait()
	void org.zhwj184.TestBean.wait(long)
	boolean org.zhwj184.TestBean.equals(java.lang.Object)
	java.lang.String org.zhwj184.TestBean.toString()
	void org.zhwj184.TestBean.notify()
	void org.zhwj184.TestBean.notifyAll()

	invoke testBean.add(1;2.4)
	result:java.lang.Float 3.4
	run time: 0ms

	invoke testBean.addBean({"c":23,"d":323.34};{"c":3433,"d":3232433.34})
	result:org.zhwj184.A {"c":3456,"d":3232756.6799999997}
	run time: 0ms

这样可以诊断spring容易中任何bean的任意方法，执行方法，看看返回结果是否跟预期的一致。一般生产环境跟测试环境很多依赖条件（数据库，服务等）都不一样，所以线上出现问题一般都可以知道参数，通过执行方法就可以看看结果是否正确，并且查看某个方法的执行时间来看方法是否有性能问题。

不过这里的代码只是简单做个示例，代码中关于参数的类型，结果匹配反射等可能不够完善也没有测试得很充分，有问题有兴趣的环境反馈给我。