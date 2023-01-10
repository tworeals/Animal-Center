import React, { useEffect, useState } from "react";
import { StatusBar } from 'expo-status-bar';
import { Dimensions , View, SafeAreaView, Text, StyleSheet } from "react-native";
import BotStackScreen from "./global/navigator/BotStackNav";
import TestScreen from "./global/navigator/TestScreen";
import { NavigationContainer } from "@react-navigation/native";

export default function App() {
  console.disableYellowBox = true;

  const windowWidth = Dimensions.get('window').width;
  const windowHeight = Dimensions.get('window').height

  return (
    // return (loading ? <LoadingPage /> : (
    <NavigationContainer>
      <SafeAreaView style={{ flex: 1, width: windowWidth, height: windowHeight }}>
        <BotStackScreen />
      </SafeAreaView>
    </NavigationContainer>
    // )
  );
}


//  return (
//    <View style={styles.container}>
//      <Text>Open up App.js to start working on your app!</Text>
//      <StatusBar style="auto" />
//    </View>
//  );
//}

//const styles = StyleSheet.create({
//  container: {
//    flex: 1,
//    backgroundColor: '#fff',
//    alignItems: 'center',
//    justifyContent: 'center',
//  },
//});


//  return (
//    <NavigationContainer>
//      <SafeAreaView style={{ flex: 1 }}>
//        <TestScreen />
//      </SafeAreaView>
//    </NavigationContainer>
//  );
// }

