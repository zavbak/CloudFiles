package com.gladkih.geekbrains.cloudfiles.server.db;

import com.gladkih.geekbrains.cloudfiles.server.mvc.model.User;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.exceptions.Exceptions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBHelperTest {


    public static void main(String[] args) {

//        Flowable<Integer> flowable = Flowable.range(10,20);
//        Flowable<Integer> flowable1 = Flowable.range(50,60);
//
//
//        flowable.zipWith(flowable1,(s, s2) -> {
//            return "" + s + " " +  s2;
//        }).reduce(0,(s, s2) -> {
//            return s +1;
//        }).subscribe((integer, throwable) -> {
//            System.out.println(integer);
//        });


//
//        Single.just("Hello")
//                .map(s -> {
//                    return s + "kjh";
//                })
//                .map(s -> {
//
//                    //throw new RuntimeException();
//                    throw Exceptions.propagate(new Throwable("Мой"));
//                })
//                .map(objectSingle -> {
//                    System.out.println(objectSingle);
//                    return objectSingle;
//                })
//                .subscribe((o, throwable) -> {
//                    System.out.println(o);
//                    System.out.println(throwable);
//                });


        try {
            DBHelper dbHelper = DBHelper.getInstance();

//            dbHelper.addUser(new User("vasa", 4654));
//            dbHelper.addUser(new User("petya", 546));
//            dbHelper.addUser(new User("sasha", 646));

//            dbHelper.getAllUser().forEach(user -> {
//                System.out.println(user);
//            });

            //dbHelper.dellUser("vasa");

//            dbHelper.getAllUser().forEach(user -> {
//                System.out.println(user);
//            });
//
//
//            dbHelper.updateUser(new User("petya",555));
//
//            dbHelper.getAllUser().forEach(user -> {
//                System.out.println(user);
//            });

//            System.out.println(dbHelper.getUser("petya"));
//
//            dbHelper.dellUser("petya");
//            System.out.println(dbHelper.getUser("petya"));

//            dbHelper.getAllUser().forEach(user -> {
//                System.out.println(user);
//            });
//
//            dbHelper.dellAllUser();
//
//
//            dbHelper.getAllUser().forEach(user -> {
//                System.out.println(user);
//            });
//
//            dbHelper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}