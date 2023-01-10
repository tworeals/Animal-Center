import React, { useState, useEffect, useCallback, useRef } from 'react';
import { ActivityIndicator, FlatList, View, Text, Image, StyleSheet, TouchableOpacity, TextInput, Pressable, Alert } from 'react-native';
import { firebase_db } from '../firebaseConfig';
import YoutubePlayer from "react-native-youtube-iframe";
import { AntDesign } from '@expo/vector-icons';
import Constants from 'expo-constants';

export default function Users() {
    //-------Flatlist 적용을 위한 useState 등 선언부분-----
    const [loading, setLoading] = useState(true); // 로딩 화면 mount시키기 위한 useState
    const [state, setState] = useState([])
    const [cardID, setCardID] = useState(["i4S5hvPG9ZY"])
    const [error, setError] = useState(null);
    const [search, setSearch] = useState('');
    const [TotalDataSource, setTotalDataSource] = useState([]);
    const [favorite, setFavorite] = useState(false);
    const [fireKey, setFireKey] = useState();
    const [playing, setPlaying] = useState(true);
    const [QueList, setQueList] = useState();
    const user_id = Constants.installationId;
    // user_id 경로 선언 부분 (push나 remove, ref 등 firebase연동에 사용)
    // -----iframe 적용부분----------------------------------

    const onStateChange = useCallback((state) => {
        if (state === "ended") {
            setPlaying(true);
        }
    }, []);

    // const togglePlaying = useCallback(() => {
    //     setPlaying((prev) => !prev);
    // }, []);


    // ---------- CardID에 videoId 할당해주는 부분
    const onPress = ({ item, index }) => {
        // console.log(index);
        // console.log(SelectedKey);
        // console.log(fireKey);
        // console.log(state[index].id.videoId)
        const result = [];
        for (let i = index; i < fireKey.length; i++) {
            result.push(state[i].id.videoId)
        }
        return (
            setQueList(result),
            setCardID(item.id.videoId)
        )
    }


    // ---Firebase를 대입하기 위한 부분 --------

    useEffect(() => {
        setTimeout(() => {
            setLoading(true);
            firebase_db
                .ref(`/like/${user_id}`)
                .on('value', (snapshot) => {
                    const Like_List = (snapshot.val());
                    if (Like_List === null) {
                        Alert.alert('<찜 없음>', '목록이 없습니다!')
                    } else {
                        setFireKey(Object.keys(Like_List)) // 데이터 내 모든 realtime database keys
                        setState(Object.values(Like_List))
                        setTotalDataSource(Object.values(Like_List));
                        setLoading(false);
                        // console.log(Object.values(Like_List));
                        // console.log(Object.values(Like_List.length));
                    }
                })
        }, 300)
    }, []);

    // Pull Down Refreshing 기능 부분 ---------------------------------------------------
    const onRefresh = async () => {
        setIsFetching(true);
        await sleep(2000);
        setIsFetching(false);
    };

    const [isFetching, setIsFetching] = useState(false);
    const sleep = (ms) => new Promise((resolve) => setTimeout(resolve, ms));

    // SearchBar 검색기능 선언부분--------------------------------------------------------

    const searchFilter = (text) => {
        if (text) {
            const newData = TotalDataSource.filter(function (item) {
                const itemData = item.snippet.title
                    ? item.snippet.title.toUpperCase()
                    : ''.toUpperCase();
                const textData = text.toUpperCase();
                return itemData.indexOf(textData) > -1;
            });
            setState(newData);
            setSearch(text);
        } else {
            setState(TotalDataSource);
            setSearch(text);
        }
    };

    // Like 관련 설정 코드 ------------------------------------------------

    function UnLike({ index }) {
        console.log(fireKey[index])
        let FBKey = fireKey[index]
        firebase_db.ref(`/like/${user_id}/${FBKey}`).remove()
            .then(() => { Alert.alert('<찜 해제 완료>'); })
        setFavorite(false);
    }

    //ActivityIndicator는 로딩 중 돌아가는 동그라미
    if (loading) {
        return (
            <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
                <ActivityIndicator size="large" color="#5500dc" />
            </View>
        );
    }


    //fetching 중 에러가 나면 나올 안내화면
    if (error) {
        return (
            <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
                <Text style={{ fontSize: 18 }}>
                    {`fetching 에러다 욘석아~
                     네트워크를 확인해보렴!`}
                </Text>
            </View>
        );
    }

    // const Favorite = ({ item }) => {
    //     if (item.id.videoId == undefined) { <Like /> }
    //     else { <UnLike /> }
    // }

    // 렌더링용 메인 return부분 ------------------------------------------------------
    return (
        <View style={styles.container}>
            <View style={styles.textContainerContainer}>
                {/* SarchBar 부분 */}
                <TextInput
                    style={styles.textContainer}
                    onChangeText={(text) => searchFilter(text)}
                    value={search}
                    underlineColorAndroid="transparent"
                    placeholder="검색어를 입력하세요!"
                    autoCorrect={true}   // 자동수정
                // autoCapitalize="none"   // 자동 대문자
                // autoComplete  // 자동완성 (Android 한정). 끄려면 off
                // clearTextOnFocus={true}  // true일 경우, 텍스트 자동지움됨
                // clearButtonMode="always"  // 텍스트 보기의 오른쪽에 지우기 버튼 표시됨. 기본값은 never
                // keyboardType="defualt"
                />
            </View>

            {/* iframe을 보여주기 위한 부분 */}
            <View>
                <YoutubePlayer
                    height={200}
                    play={playing}
                    videoId={cardID}
                    onChangeState={onStateChange}
                    playList={QueList}
                />
                {/* <Button title={playing ? "pause" : "play"} onPress={togglePlaying} /> */}
            </View>


            {/* Flatlist 부분 */}
            <FlatList
                data={state}
                onRefresh={onRefresh}
                refreshing={isFetching}
                // ItemSeparatorComponent={ItemSeparatorView}
                keyExtractor={(item, index) => index.toString()}
                renderItem={({ item, index }) => (
                    <View style={styles.cardContainer}>
                        <TouchableOpacity style={styles.card} onPress={() => onPress({ item, index })}>
                            <Image style={styles.cardImage} source={{ uri: item.snippet.thumbnails.medium.url }} />
                            <View style={styles.cardText}>
                                <Text style={styles.cardTitle} numberOfLines={1}>{item.snippet.title}</Text>
                                <Text style={styles.cardDesc} numberOfLines={3}>{item.snippet.description}</Text>
                                <Text style={styles.cardDate}>{item.snippet.publishedAt}</Text>
                                <Text style={styles.cardDate}>{item.id.videoId}</Text>
                                {/* <Text style={styles.cardDate}>{index}</Text> */}
                            </View>
                            <View style={styles.LikeButton}>
                                <View style={styles.heartBotton}>
                                    {/* favorite 대신에 cardID가 있는지 유무 확인 */}
                                    {favorite ?
                                        <Pressable onPress={() => Like({ item })} >
                                            <AntDesign name="heart" size={30} color="#eb4b4b" />
                                        </Pressable>
                                        :
                                        <Pressable onPress={() => UnLike({ index })} >
                                            <AntDesign name="hearto" size={30} color="#999" />
                                        </Pressable>
                                    }
                                </View>
                            </View>
                            {/* <View style={styles.heartBotton}>
                                <Pressable onPress={() => Like({ item })}>
                                    {favorite ?
                                        <AntDesign name="heart" size={30} color="#eb4b4b" />
                                        :
                                        <AntDesign name="hearto" size={30} color="#999" />
                                    }
                                </Pressable>
                            </View> */}
                        </TouchableOpacity>
                    </View >
                )
                } />
        </View >
    );
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#000',
    },
    cardContainer: {
        backgroundColor: '#000',
    },
    card: {
        flex: 1,
        //컨텐츠들을 가로로 나열
        //세로로 나열은 column <- 디폴트 값임
        flexDirection: "row",
        margin: 10,
        borderBottomWidth: 0.5,
        borderBottomColor: "#A6A6A6",
        paddingBottom: 10
    },
    cardImage: {
        flex: 1,
        width: 100,
        height: 100,
        borderRadius: 10,
    },
    cardText: {
        flex: 2,
        flexDirection: "column",
        marginLeft: 10,

    },
    cardTitle: {
        fontSize: 20,
        fontWeight: "700",
        color: '#fff'
    },
    cardDesc: {
        fontSize: 15,
        color: '#A6A6A6'
    },
    cardDate: {
        fontSize: 10,
        color: "#A6A6A6",
    },
    textContainerContainer: {
        backgroundColor: '#fff',
        fontSize: 20,
        margin: 5,
    },
    textContainer: {
        backgroundColor: '#fff',
        paddingHorizontal: 5,
        fontSize: 20,
        margin: 5,
    },
    heartBotton: {
        alignItems: "center",
        justifyContent: "center"
    },
    LikeButton: {
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center"
    }
});