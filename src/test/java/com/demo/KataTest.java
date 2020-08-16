package com.demo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.assertj.core.api.Assertions.*;


//shortcurs
//Control+Shit+R --> Ejecutar los test.
//Option + Enter --> desplegar opciones como crear clase o crear metodo.
//shift+command+t --> Crear o navegar a los test.

public class KataTest {
    @Test
    public void test_Junit() {
        assertEquals(0,0);
    }


    @Test
    public void test_assertJ() {
        assertThat("foo").isEqualTo("foo");
    }

    @Test
    public void assertJ_exception() {
        /*assertThatThrownBy(() -> registerAUser.execute(username))
                .isInstanceOf(AlreadyRegisteredUser.class);*/
    }


    /* ejemplo usando mockito

    @Before
    public void setUp() throws Exception {
        users = new InMemoryUsers();
        followUserView = mock(IFollowUserView.class);
        followAUser = spy(new FollowAUser(users));
        presenterFollowUser = new PresenterFollowUser(followUserView,followAUser);
    }

    @Test
    public void an_user_can_follows_another_user() {
        String username1 = "user1";
        String username2 = "user2";

        doNothing().when(followAUser).execute(username1,username2);
        presenterFollowUser.followUser(username1, username2);

        verify(followAUser,times(1)).execute(username1,username2);
        verify(followUserView,times(1)).showSuccesFollowUser(username2);
    }
     */
}
