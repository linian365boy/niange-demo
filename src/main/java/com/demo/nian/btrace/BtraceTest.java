package com.demo.nian.btrace;

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.OnTimer;
import com.sun.btrace.annotations.Return;
import com.sun.btrace.annotations.Self;
import static com.sun.btrace.BTraceUtils.*;

@BTrace
public class BtraceTest {
	
	@OnMethod(
			clazz="com.demo.nian.btrace.MainTest",
			method="execute",
			location=@Location(Kind.RETURN)
	)
	
	@OnTimer(4000)
	public static void printM(@Self com.demo.nian.btrace.MainTest mainTest, 
			int sleepTime, @Return boolean result){
		println("-=-=-=-");
		println(strcat("result=",str(result)));
		println(strcat("sleepTime=",str(sleepTime)));
	}
}
