package com.groovy.test

import groovy.json.JsonOutput;
import groovy.json.JsonSlurper;
import groovy.sql.Sql;
import groovy.swing.SwingBuilder;

import java.awt.GridLayout;
import java.awt.TextField;
import java.lang.invoke.SwitchPoint;
import java.lang.management.ManagementFactory;

import javax.swing.*;
import javax.awt.*;

import org.codehaus.groovy.transform.trait.Traits.Implemented;

first = 1;
second = 1;
20.times {
	println first
	_s = second + first
	first = second
	second = _s
}

Money m = new Money(101);
m  = m + 1 + 2 + m +  3 + 4 + 5.0;
println "fuck $m.amount"

def roman = ['', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII']
println roman.size()
println roman[8]
println roman.size()

http = [
	1 : 'baidu',
	2 : 'google',
	]
println http[1]
println http.size()
http.each { e, f -> print e + ':' + f + " "}

x = 1..10
x.each{ e -> print e * 10 + ' ' }
println ''
for(i in x){
	println i * 10
}

date = new Date()
str = "now ${date.getTime()}, Hello World!"
println str
println str.strings[0]
str <<= ''
println str
str[1..2] = 'i hao,'
println str
str = 'she selles sea shells at the sea shore of seychelles'
finder = str =~ /s.e/
println str ==~ /s.e/
finder.each{ e -> print e + ' \n'}


l = 0..1000
s = l.findAll{ it < 10 }
s << 'a'
println  s
//s.each { m.&say(it) }
methodName = "say"
s.each { m."$methodName"(it) }

def miao(x, Closure method){ method(x) }
def adder = { x, y -> return x + y }
println miao(555, m.&say)
println adder(666, 777)

def addOne = adder.curry(1)
println addOne(666)


class Faker{
	int a = 1;
	int b(){
		return 2;
	}
	Closure c(d){
		def e = 3;
		def r = {
			caller -> [this, a, b(), e, d, caller, owner]
		}
		return r;
	}
}
Faker faker = new Faker()
cs = faker.c(4)
cs = cs.call(this)
cs.each { println it }
println cs[5] instanceof Script

switch(new Money(10086)){
	case m:
		println "fuck you";
		break;
}

m = [10086] as Money
m?.say('fuck you')

ms = 0..10
println ms[0..3]

ms = ['1','2','2','3','1']
ms.remove('1')
println ms

println ms.intersect(['1','9'])

def foo(n){
	println 'n -> ' + n
	return { println 'it -> ' + it + ' n -> ' + n; n += it; }
}
def accumulite = foo(1)
println accumulite(2)
println accumulite(1)

Money moeny = [10000]
println moeny.amount

list = []
for(int i = 1; i <= 10; ++ i){
	list += [ i * 1000 ] as Money
}
println list*.amount

file = new File('C:/Users/jiawei/Desktop/测试.txt')
file.eachLine('GBK'){
	line -> println line
}
file.withWriter('GBK') {
	writer -> list*.amount.each {
		writer.writeLine(it.toString())
	}
}


trait Animal{
	String name;
	
	void eat(){
		println "$name now is eating..."
	}
	
	void sleep(){
		println "$name now is sleeping..."
	}
}

class Cat implements Animal{
	
	Cat(String name){
		this.name = name;
	}
	
}

Cat cat = new Cat('Milu')
cat.eat()
cat.sleep()

def os = ManagementFactory.operatingSystemMXBean
println """OPERATING SYSTEM:
	OS architecture = $os.arch 
	OS name = $os.name 
	OS version = $os.version 
	OS processors = $os.availableProcessors 
""" 

def rt = ManagementFactory.runtimeMXBean
println """RUNTIME:
   	Runtime name = $rt.name 
   	Runtime spec name = $rt.specName 
   	Runtime vendor = $rt.specVendor 
   	Runtime spec version = $rt.specVersion 
   	Runtime management spec version = $rt.managementSpecVersion 
   """

   def mem = ManagementFactory.memoryMXBean
   def heapUsage = mem.heapMemoryUsage
   def nonHeapUsage = mem.nonHeapMemoryUsage
   
   println """MEMORY:
   HEAP STORAGE: 
      	Memory committed = $heapUsage.committed 
      	Memory init = $heapUsage.init 
      	Memory max = $heapUsage.max 
      	Memory used = $heapUsage.used NON-HEAP STORAGE: 
      	Non-heap memory committed = $nonHeapUsage.committed 
      	Non-heap memory init = $nonHeapUsage.init 
      	Non-heap memory max = $nonHeapUsage.max 
      	Non-heap memory used = $nonHeapUsage.used 
   """
	  
	println "GARBAGE COLLECTION:"
	ManagementFactory.garbageCollectorMXBeans.each { gc ->
	   println "	name = $gc.name"
	   println "		collection count = $gc.collectionCount"
	   println "		collection time = $gc.collectionTime"
	   String[] mpoolNames =   gc.memoryPoolNames
		
	   mpoolNames.each {
		  mpoolName -> println "		mpool name = $mpoolName"
	   }
	}
	
json = new JsonSlurper()
obj = json.parseText('{ "name": "John", "ID" : "1"}')
println obj.name
println JsonOutput.toJson(os)

sql = Sql.newInstance('jdbc:mysql://192.168.1.106:3306/arpg', 'root', 'sijieshidai', 'com.mysql.jdbc.Driver')
sql.eachRow('select version()') {
	println it[0]
}
sql.close()

app = new SwingBuilder()
frame = app.frame(title:'骚年来一发么~', size:[300, 200], defaultCloseOperation : WindowConstants.EXIT_ON_CLOSE){
	panel(layout : new GridLayout(3,2,5,5)){
			label(text : 'Hello World', horizontalAlignment:JLabel.RIGHT)
			textField(text : '你', columns : 10)
			label(text : 'Hello World', horizontalAlignment:JLabel.RIGHT)
			textField(text : '是', columns : 10)
			label(text : 'Hello World', horizontalAlignment:JLabel.RIGHT)
			textField(text : '谁', columns : 10)
		}
}
frame.setVisible(false)