package com.example.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;


public class MockitoTest {

    @Mock
    List<String> mockList;

    @Spy
    List<Integer> spyList;

    @BeforeEach
    void init() {
        openMocks(this);
    }

    @Test
    @DisplayName("SpyTest")
    void spyTest() {
        spyList = new ArrayList<>() {{
            add(1);
            add(2);
        }};

        assertEquals(2, spyList.size());
    }


    @Test
    @DisplayName("mockTest")
    void mockTestWithWhenAndDoReturn() {
        //When example
        Mockito.when(mockList.get(anyInt())).thenReturn("mock");

        //Do return example
        Mockito.doReturn("mock").when(mockList).get(anyInt());

        assertEquals("mock", mockList.get(78));
    }


    @Test
    @DisplayName("ExceptionTest")
    void doThrowTest() {
        //DoThrow example
        doThrow(NullPointerException.class).when(mockList).size();

        //thenThrow Example
        Mockito.when(mockList.size()).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> mockList.size());
    }


    @Test
    @DisplayName("doAnswerTest")
    public void AnswerInvocation() {
        Mockito.doAnswer(invocation -> {
            int arg = invocation.getArgument(0);
            return arg*arg;
        }).when(mockList).get(anyInt());

        assertEquals(16,mockList.get(4));
    }


    @Test
    @DisplayName("verifyTest")
    public void verifyWithTimes() {
       mockList.add("1");
       mockList.add("2");

       Mockito.verify(mockList,times(2)).add(anyString());

    }

    @Test
    @DisplayName("inOrderTest")
    public void inOrder(){
        mockList.add("1");
        mockList.set(0,"2");
        mockList.clear();

        InOrder inOrder = Mockito.inOrder(mockList);
        inOrder.verify(mockList).add(anyString());
        inOrder.verify(mockList).set(anyInt(),anyString());
        inOrder.verify(mockList).clear();
    }


}


