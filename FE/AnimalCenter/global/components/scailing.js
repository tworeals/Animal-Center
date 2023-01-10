import { Dimensions } from 'react-native';
const { width, height } = Dimensions.get('window');

// 내가 개발 테스트 한 모바일의 실제 가로 세로 상수값 기입
const guidelineBaseWidth = 411.42857142857144;
const guidelineBaseHeight = 797.7142857142857;
const guideScale = Math.sqrt(guidelineBaseWidth * guidelineBaseHeight)

const scale = Math.sqrt(width * height) / guideScale;
const horiPer = width / guidelineBaseWidth;
const vertiPer = height / guidelineBaseHeight;


const verticalScale = size => horiPer * size;
const horizontalScale = size => vertiPer * size;
const moderateScale = size => scale * size;

export {moderateScale, verticalScale, horizontalScale};