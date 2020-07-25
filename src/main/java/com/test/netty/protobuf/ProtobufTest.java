package com.test.netty.protobuf;/*
 *@author:
 *@time
 */

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtobufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        DateInfo.Student student = DateInfo.Student.newBuilder().setName("张三").setAge(11).setAddress("上海").build();
        byte[] studentByte=student.toByteArray();
        DateInfo.Student student1=DateInfo.Student.parseFrom(studentByte);
        System.out.println(student1);
        System.out.println(student1.getName());
        
    }
}
