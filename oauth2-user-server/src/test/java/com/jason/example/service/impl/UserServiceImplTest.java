package com.jason.example.service.impl;

import com.jason.example.common.enums.EnumSex;
import com.jason.example.common.enums.VipEnum;
import com.jason.example.dao.UserDao;
import com.jason.example.domain.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock private UserDao userDao;
//    private AutoCloseable autoCloseable;
    private UserServiceImpl underTest;

    @BeforeEach
    void setUp() {
//        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new UserServiceImpl(userDao);
    }

    // 当使用 ExtendWith MockitoExtension.class 时 就不用 AutoCloseable了
//    @AfterEach
//    void tearDown() throws Exception {
//        autoCloseable.close();
//    }

    @Test
    void getLists() {
        //when
        underTest.getLists();
        //then
        Mockito.verify(userDao).findAll();
    }

    @Test
    void willThrowExceptionSaveUser() {
        //given
        User jason = new User().builder().sex(EnumSex.Male)
                .name("Jason").phid(12345L).vip(VipEnum.Red).build();
        //不加given 你执行save 方法 返回的 默认为空的 基础数据类型就默认值
        given(userDao.saveAndFlush(jason)).willReturn(jason);
        //when
        //then
        assertThatThrownBy(()->underTest.save(jason))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("大妖怪是不能被捕捉的");
        //模拟的 永远也保存不了user
//        verify(userDao,never()).saveAndFlush(any());
    }

    @Test
    void canSaveUser() {
        //given
        User jason = new User().builder().sex(EnumSex.Male)
                .name("jason").phid(12345L).vip(VipEnum.Red).build();
        given(userDao.saveAndFlush(jason)).willReturn(jason);
        //when
        underTest.save(jason);
        //then
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        Mockito.verify(userDao).saveAndFlush(argumentCaptor.capture());
        User captureUser = argumentCaptor.getValue();

        assertThat(captureUser).isEqualTo(jason);
    }

    @Test
    void canDeleteUser() {
        //when
        long l = anyLong();
        underTest.delete(l);
        //then
        Mockito.verify(userDao).deleteById(l);

    }
}