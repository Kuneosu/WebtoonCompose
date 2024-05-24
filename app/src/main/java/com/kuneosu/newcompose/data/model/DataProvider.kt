package com.kuneosu.newcompose.data.model

import com.kuneosu.newcompose.data.room.BigToon
import com.kuneosu.newcompose.data.room.SmallToon

object DataProvider {
    val bigToonList = listOf(
        BigToon(
            title = "나 혼자만 레벨업",
            titleImage = "https://i.ibb.co/m8nCdsY/b-toon-title-1.png",
            subTitle = "현군, 장성락(REDICE STUDIO), 추공",
            backgroundImage = "https://i.ibb.co/xjsvpdP/b-toon-back-1.jpg",
            mainGIF = "https://i.ibb.co/tp89pPc/b-toon-main-1.gif",
            toonUrl = "https://webtoon.kakao.com/content/%EB%82%98-%ED%98%BC%EC%9E%90%EB%A7%8C-%EB%A0%88%EB%B2%A8%EC%97%85/2320"
        ),
        BigToon(
            title = "뒤끝작렬",
            titleImage = "https://i.ibb.co/FnD9CLT/b-toon-title-2.png",
            subTitle = "조석호, 시나브로",
            backgroundImage = "https://i.ibb.co/k9XhtMv/b-toon-back-2.webp",
            mainGIF = "https://i.ibb.co/v4xMPhj/b-toon-main-2.gif",
            toonUrl = "https://webtoon.kakao.com/content/%EB%92%A4%EB%81%9D%EC%9E%91%EB%A0%AC/2666"
        ),
        BigToon(
            title = "검술명가 막내아들",
            titleImage = "https://i.ibb.co/9W50XtG/b-toon-title-3.webp",
            subTitle = "AZI, COBY(Contentslabblue), 조고미, 이제원, 황제펭권",
            backgroundImage = "https://i.ibb.co/BN9X4sV/b-toon-back-3.webp",
            mainGIF = "https://i.ibb.co/8bbdbd6/b-toon-main-3.gif",
            toonUrl = "https://webtoon.kakao.com/content/%EA%B2%80%EC%88%A0%EB%AA%85%EA%B0%80-%EB%A7%89%EB%82%B4%EC%95%84%EB%93%A4/2852"
        ),
    )

    val reverseBigToonList = bigToonList.reversed()

    val shuffleBigToonList = bigToonList.shuffled()

    val shuffleBigToonList2 = shuffleBigToonList.shuffled()

    val smallToonList = listOf(
        SmallToon(
            title = "접경지역의 동물병원",
            mainImage = "https://i.ibb.co/rykcppB/s-toon-1.png",
            toonUrl = "https://webtoon.kakao.com/content/%EC%A0%91%EA%B2%BD%EC%A7%80%EC%97%AD%EC%9D%98-%EB%8F%99%EB%AC%BC%EB%B3%91%EC%9B%90/2443"
        ),
        SmallToon(
            title = "픽 미 업!",
            mainImage = "https://i.ibb.co/5MLvzXQ/s-toon-2.png",
            toonUrl = "https://webtoon.kakao.com/content/%ED%94%BD-%EB%AF%B8-%EC%97%85/3205"
        ),
        SmallToon(
            title = "폭군의 비서관이 되었습니다 [19세 완전판]",
            mainImage = "https://i.ibb.co/3WQCG1d/s-toon-3.png",
            toonUrl = "https://webtoon.kakao.com/content/%ED%8F%AD%EA%B5%B0%EC%9D%98-%EB%B9%84%EC%84%9C%EA%B4%80%EC%9D%B4-%EB%90%98%EC%97%88%EC%8A%B5%EB%8B%88%EB%8B%A4/2645"
        ),
        SmallToon(
            title = "철혈검가 사냥개의 회귀",
            mainImage = "https://i.ibb.co/swtkhq6/s-toon-4.png",
            toonUrl = "https://webtoon.kakao.com/content/%EC%B2%A0%ED%98%88%EA%B2%80%EA%B0%80-%EC%82%AC%EB%83%A5%EA%B0%9C%EC%9D%98-%ED%9A%8C%EA%B7%80/3455"
        ),
        SmallToon(
            title = "이 결혼은 어차피 망하게 되어 있다 [19세 완전판]",
            mainImage = "https://i.ibb.co/X2xDXBZ/s-toon-5.png",
            toonUrl = "https://webtoon.kakao.com/content/%EC%9D%B4-%EA%B2%B0%ED%98%BC%EC%9D%80-%EC%96%B4%EC%B0%A8%ED%94%BC-%EB%A7%9D%ED%95%98%EA%B2%8C-%EB%90%98%EC%96%B4-%EC%9E%88%EB%8B%A4/3066"
        ),
        SmallToon(
            title = "악역의 엔딩은 죽음뿐",
            mainImage = "https://i.ibb.co/Vv6LLd1/s-toon-6.png",
            toonUrl = "https://webtoon.kakao.com/content/%EC%95%85%EC%97%AD%EC%9D%98-%EC%97%94%EB%94%A9%EC%9D%80-%EC%A3%BD%EC%9D%8C%EB%BF%90/2383"
        ),
        SmallToon(
            title = "무지개다리 파수꾼",
            mainImage = "https://i.ibb.co/vZZ9mX7/s-toon-7.png",
            toonUrl = "https://webtoon.kakao.com/content/%EB%AC%B4%EC%A7%80%EA%B0%9C%EB%8B%A4%EB%A6%AC-%ED%8C%8C%EC%88%98%EA%BE%BC/2043"
        ),
        SmallToon(
            title = "토끼와 흑표범의 공생관계",
            mainImage = "https://i.ibb.co/Y3y0qj3/s-toon-8.png",
            toonUrl = "https://webtoon.kakao.com/content/%ED%86%A0%EB%81%BC%EC%99%80-%ED%9D%91%ED%91%9C%EB%B2%94%EC%9D%98-%EA%B3%B5%EC%83%9D%EA%B4%80%EA%B3%84/2567"
        ),
        SmallToon(
            title = "공주보다 시녀가 천직이었습니다",
            mainImage = "https://i.ibb.co/YPk399w/s-toon-9.png",
            toonUrl = "https://webtoon.kakao.com/content/%EA%B3%B5%EC%A3%BC%EB%B3%B4%EB%8B%A4-%EC%8B%9C%EB%85%80%EA%B0%80-%EC%B2%9C%EC%A7%81%EC%9D%B4%EC%97%88%EC%8A%B5%EB%8B%88%EB%8B%A4/3971"
        ),
        SmallToon(
            title = "가짜 애첩의 화려한 일상 [19세 완전판]",
            mainImage = "https://i.ibb.co/kKCz2KY/s-toon-10.png",
            toonUrl = "https://webtoon.kakao.com/content/%EA%B0%80%EC%A7%9C-%EC%95%A0%EC%B2%A9%EC%9D%98-%ED%99%94%EB%A0%A4%ED%95%9C-%EC%9D%BC%EC%83%81/3274"
        ),
        SmallToon(
            title = "두 번 사는 랭커",
            mainImage = "https://i.ibb.co/8bbFyTV/s-toon-11.png",
            toonUrl = "https://webtoon.kakao.com/content/%EB%91%90-%EB%B2%88-%EC%82%AC%EB%8A%94-%EB%9E%AD%EC%BB%A4/2335"
        ),
        SmallToon(
            title = "올 힘 마법사",
            mainImage = "https://i.ibb.co/jHDswXW/s-toon-12.png",
            toonUrl = "https://webtoon.kakao.com/content/%EC%98%AC-%ED%9E%98-%EB%A7%88%EB%B2%95%EC%82%AC/4000"
        ),

        SmallToon(
            title = "권왕전생",
            mainImage = "https://i.ibb.co/k4FJKys/s-toon-13.png",
            toonUrl = "https://webtoon.kakao.com/content/%EA%B6%8C%EC%99%95%EC%A0%84%EC%83%9D/4013"
        ),
        SmallToon(
            title = "저승식당",
            mainImage = "https://i.ibb.co/0jrdDv7/s-toon-14.png",
            toonUrl = "https://webtoon.kakao.com/content/%EC%A0%80%EC%8A%B9%EC%8B%9D%EB%8B%B9/3353"
        ),
        SmallToon(
            title = "아비무쌍",
            mainImage = "https://i.ibb.co/wQVPVWC/s-toon-15.png",
            toonUrl = "https://webtoon.kakao.com/content/%EC%95%84%EB%B9%84%EB%AC%B4%EC%8C%8D/1395"
        ),
        SmallToon(
            title = "SSS급 랭커 회귀하다",
            mainImage = "https://i.ibb.co/6mthBG2/s-toon-16.png",
            toonUrl = "https://webtoon.kakao.com/content/SSS%EA%B8%89-%EB%9E%AD%EC%BB%A4-%ED%9A%8C%EA%B7%80%ED%95%98%EB%8B%A4/3197"
        ),
        SmallToon(
            title = "로드 오브 머니",
            mainImage = "https://i.ibb.co/FVtQ21Q/s-toon-17.png",
            toonUrl = "https://webtoon.kakao.com/content/%EB%A1%9C%EB%93%9C-%EC%98%A4%EB%B8%8C-%EB%A8%B8%EB%8B%88/2453"
        ),
        SmallToon(
            title = "던전 리셋",
            mainImage = "https://i.ibb.co/55QvcfJ/s-toon-18.png",
            toonUrl = "https://webtoon.kakao.com/content/%EB%8D%98%EC%A0%84-%EB%A6%AC%EC%85%8B/2373"
        ),
        SmallToon(
            title = "남주의 입양딸이 되었습니다",
            mainImage = "https://i.ibb.co/F6GVrm6/s-toon-19.png",
            toonUrl = "https://webtoon.kakao.com/content/%EB%82%A8%EC%A3%BC%EC%9D%98-%EC%9E%85%EC%96%91%EB%94%B8%EC%9D%B4-%EB%90%98%EC%97%88%EC%8A%B5%EB%8B%88%EB%8B%A4/2664"
        ),
        SmallToon(
            title = "지옥사원",
            mainImage = "https://i.ibb.co/HFSmcL6/s-toon-20.png",
            toonUrl = "https://webtoon.kakao.com/content/%EC%A7%80%EC%98%A5%EC%82%AC%EC%9B%90/1369"
        ),
        SmallToon(
            title = "부서진 성좌의 회귀",
            mainImage = "https://i.ibb.co/3znzYLs/s-toon-21.png",
            toonUrl = "https://webtoon.kakao.com/content/%EB%B6%80%EC%84%9C%EC%A7%84-%EC%84%B1%EC%A2%8C%EC%9D%98-%ED%9A%8C%EA%B7%80/3020"
        ),
        SmallToon(
            title = "블러디 체어",
            mainImage = "https://i.ibb.co/VtycBXf/s-toon-22.png",
            toonUrl = "https://webtoon.kakao.com/content/%EB%B8%94%EB%9F%AC%EB%94%94-%EC%B2%B4%EC%96%B4/3541"
        ),
        SmallToon(
            title = "사실은 내가 진짜였다",
            mainImage = "https://i.ibb.co/yYF6qJ9/s-toon-23.png",
            toonUrl = "https://webtoon.kakao.com/content/%EC%82%AC%EC%8B%A4%EC%9D%80-%EB%82%B4%EA%B0%80-%EC%A7%84%EC%A7%9C%EC%98%80%EB%8B%A4/2424"
        ),
        SmallToon(
            title = "딩스뚱스",
            mainImage = "https://i.ibb.co/s1s5XNJ/s-toon-24.png",
            toonUrl = "https://webtoon.kakao.com/content/%EB%94%A9%EC%8A%A4%EB%9A%B1%EC%8A%A4/760"
        ),
    )

    val reverseSmallToonList = smallToonList.reversed()

    val shuffleSmallToonList = smallToonList.shuffled()

    val shuffleSmallToonList2 = shuffleSmallToonList.shuffled()
}
