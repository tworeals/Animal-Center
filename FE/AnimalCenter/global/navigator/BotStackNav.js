import React, { useEffect, useState } from "react";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import { createStackNavigator } from "@react-navigation/stack";
import { MaterialCommunityIcons } from '@expo/vector-icons';
import FireFlat from "../../pages/FireFlat";
import TestPage from "../../pages/testPages";
import HomePage from "../../pages/HomeFlat";

// TabStack은 TabBar를 만드는 네비게이션옵션을 선언해준거다.
// webviewStack은 webview를 StackNavigation해주기 위해 선언해준거다.
// 그밖의 Stack옵션을 넣어주고 싶은 화면은 FBStack쓰면 된다.
const TabStack = createBottomTabNavigator();
const FBStack = createStackNavigator();

// 일반 화면을 stackscreen화 시키기 위해 선언하는 곳
const FBStackScreen = () => {
    return (
        <FBStack.Navigator>
            <FBStack.Screen
                name="about"
                component={TestPage}
                options={{ headerShown: false }}
            />
        </FBStack.Navigator>
    );
};

const HomeStackScreen = () => {
    return (
        <FBStack.Navigator>
            <FBStack.Screen
                name="about"
                component={TestPage}
                options={{ headerShown: false }}
            />
        </FBStack.Navigator>
    );
};


// 아래쪽 탭을 만들어주는 navigation. 위에서 선언해서 만들어 놓은 stackScreen들을 받아와서 구성한다.
// 새로만들어주고 싶다면 아래 코드를 복붙하고, name을 선언해주고,
// component는 새로 선언해둔 stackscreen을 가져와주고, tabBarLabel로 원하는대로 탭이름 설정해주고,
// MaterialCommunityIcons name으로 원하는 아이콘 설정해주자.
const TabStackScreen = () => {
    return (
        <TabStack.Navigator screenOptions={{ tabBarActiveTintColor: '#eb4b4b', headerShown: false }} backBehavior="history" initialRouteName="홈" sceneContainerStyle={{ marginTop: 30 }}>
            <TabStack.Screen name="홈" component={HomeStackScreen} options={{ tabBarLabel: 'Home', tabBarIcon: ({ color, size }) => (<MaterialCommunityIcons name="home" color={color} size={size} />), }} />
            <TabStack.Screen name="파이어베이스" component={FBStackScreen} options={{ tabBarLabel: 'Firebase', tabBarIcon: ({ color, size }) => (<MaterialCommunityIcons name="cloud" color={color} size={size} />), }} />
        </TabStack.Navigator>
    );
};

export default TabStackScreen;