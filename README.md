# Retrofit - GET

기본적인 UI와 각종 이벤트를 통해 어플을 생성했다면 이제는 서버와 연동하여 실시간 데이터를 처리하는 기능이 필요할 때이다. httpURLConnection, OkHttp 등 다양한 방식으로 통신을 할 수 있지만 가장 빠르고 편리한 Retrofit을 통해 통신을 해볼까 한다.

## 설치

### 1. Internet 권한 추가

**AndroidManifest.xml**

![manifest](https://github.com/Ekutz/Retrofit-Get/blob/master/1.%20manifest.png?raw=true)

```
<uses-permission android:name="android.permission.INTERNET"/>

```

### 2. gradle 추가

다른 라이브러리와 마찬가지로 Retrofit 역시 gradle을 통해 라이브러리를 추가해주어야 한다. 

![gradle](https://github.com/Ekutz/Retrofit-Get/blob/master/2.%20gradle.png?raw=true)

```
implementation 'com.squareup.retrofit2:retrofit:2.4.0'
implementation 'com.squareup.retrofit2:converter-gson:2.4.0'

```

converter-gson이 뭔지는 아래에서 설명한다.

### 3. Retrofit 인스턴스와 인터페이스 만들기

굳이 여러 통신 객체가 존재해야 할 필요가 없고 속도 향상을 위해 Retrofit을 싱글톤 패턴으로 만들어 준다.

![instance](https://github.com/Ekutz/Retrofit-Get/blob/master/3.%20instance.png?raw=true)

## Api

### 4. api 준비하기

서버에 api가 준비되어 있다면 이 과정을 뛰어 넘어도 괜찮지만 예제를 위해 [서울시 열린 데이터광장](http://data.seoul.go.kr/dataList/datasetView.do?infId=OA-13122&srvType=A&serviceKind=1&currentPageNo=1&searchValue=&searchKey=null)의 api를 사용해보자 (서울시 주차장 정보 api)

가입 후 인증키를 복사하여 샘플 url에 넣고 주소창에 넣어보자

![xml](https://github.com/Ekutz/Retrofit-Get/blob/master/5.%20xml.png?raw=true)

보기에도 답답한 이상한 결과값이 출력된다

우리가 원하는 결과값은 형태는 json 형식이기 때문이다.

![json](https://github.com/Ekutz/Retrofit-Get/blob/master/6.%20json.png?raw=true)

빨간 동그라미에 있던 xml을 json으로 바꾸면 json 형식의 결과값이 출력된다.

### 5. 결과값 예쁘게 보기

json 형식으로 결과값을 얻어냈지만 아직도 결과값은 너무도 보기에 안쓰럽다. 이러한 json 형식을 보기 좋게 만들어보자.

[Json Beautifier](http://json.parser.online.fr)

![beautify](https://github.com/Ekutz/Retrofit-Get/blob/master/7.%20beautify.png?raw=true)

난잡하던 결과값이 조금 보기 쉬운 형태로 바뀐 것을 알 수 있다.

## 연동하기

Retrofit과 api가 모두 준비되었다면 어플을 통해 연동을 해본다.

### 6. Base Url 준비하기

**RetrofitUtil.java**

![baseurl](https://github.com/Ekutz/Retrofit-Get/blob/master/8.%20baseurl.png?raw=true)

여기서 converter-gson을 설명하자면 결과값으로 나온 json을 자바가 읽을 수 있도록 바꿔주는 컨버터이다.

### 7. 결과값을 받을 객체 만들어 두기

json 형식의 결과값을 받아서 담아줄 객체를 미리 만들어 둔다. 각 변수명은 결과값에서 보내주는 변수명과 일치해야 한다. 그렇지 않을 경우 일일히 serializeName을 어노테이션으로 달아줘야 하는 수고로움을 겪어야 한다.

![response](https://github.com/Ekutz/Retrofit-Get/blob/master/9.%20response.png?raw=true)

json beautify를 통해 보았던 json을 형태와 같은 형태의 POJO 파일을 만들어 준다.

**ResponseParkInfo.java**

```
private com.example.ekutz.retorfittest.response.GetParkInfo.GetParkInfo GetParkInfo;

public com.example.ekutz.retorfittest.response.GetParkInfo.GetParkInfo getGetParkInfo() {
    return GetParkInfo;
}

```

**GetParkInfo.java**

```
public class GetParkInfo {

    private int list_total_count;
    private Result_park RESULT;
    private List<RowData> row;

    public int getList_total_count() {
        return list_total_count;
    }

    public Result_park getRESULT() {
        return RESULT;
    }

    public List<RowData> getRow() {
        return row;
    }
}

```

**Result_park.java**

```
public class Result_park {
    private String CODE;
    private String MESSAGE;

    public String getCODE() {
        return CODE;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }
}

```

**RowData.java**

```
public class RowData {
    private String PARKING_CODE;
    private String PARKING_NAME;
    private String ADDR;
    private String PARKING_TYPE;
    private String PARKING_TYPE_NM;
    private String OPERATION_RULE;
    private String OPERATION_RULE_NM;
    private String TEL;
    private String QUE_STATUS;
    private String QUE_STATUS_NM;
    private String CAPACITY;
    private String CUR_PARKING;
    private String CUR_PARKING_TIME;
    private String PAY_YN;
    private String PAY_NM;
    private String NIGHT_FREE_OPEN;
    private String NIGHT_FREE_OPEN_NM;
    private String WEEKDAY_BEGIN_TIME;
    private String WEEKDAY_END_TIME;
    private String WEEKEND_BEGIN_TIME;
    private String WEEKEND_END_TIME;
    private String HOLIDAY_BEGIN_TIME;
    private String HOLIDAY_END_TIME;
    private String SYNC_TIME;
    private String SATURDAY_PAY_YN;
    private String SATURDAY_PAY_NM;
    private String HOLIDAY_PAY_YN;
    private String HOLIDAY_PAY_NM;
    private String FULLTIME_MONTHLY;
    private String GRP_PARKNM;
    private String RATES;
    private String TIME_RATE;
    private String ADD_RATES;
    private String ADD_TIME_RATE;
    private String BUS_RATES;
    private String BUS_TIME_RATE;
    private String BUS_ADD_TIME_RATE;
    private String BUS_ADD_RATES;
    private String DAY_MAXIMUM;
    private String LAT;
    private String LNG;
    private String ASSIGN_CODE;
    private String ASSIGN_CODE_NM;

    public String getPARKING_CODE() {
        return PARKING_CODE;
    }

    public String getPARKING_NAME() {
        return PARKING_NAME;
    }

    public String getADDR() {
        return ADDR;
    }

    public String getPARKING_TYPE() {
        return PARKING_TYPE;
    }

    public String getPARKING_TYPE_NM() {
        return PARKING_TYPE_NM;
    }

    public String getOPERATION_RULE() {
        return OPERATION_RULE;
    }

    public String getOPERATION_RULE_NM() {
        return OPERATION_RULE_NM;
    }

    public String getTEL() {
        return TEL;
    }

    public String getQUE_STATUS() {
        return QUE_STATUS;
    }

    public String getQUE_STATUS_NM() {
        return QUE_STATUS_NM;
    }

    public String getCAPACITY() {
        return CAPACITY;
    }

    public String getCUR_PARKING() {
        return CUR_PARKING;
    }

    public String getCUR_PARKING_TIME() {
        return CUR_PARKING_TIME;
    }

    public String getPAY_YN() {
        return PAY_YN;
    }

    public String getPAY_NM() {
        return PAY_NM;
    }

    public String getNIGHT_FREE_OPEN() {
        return NIGHT_FREE_OPEN;
    }

    public String getNIGHT_FREE_OPEN_NM() {
        return NIGHT_FREE_OPEN_NM;
    }

    public String getWEEKDAY_BEGIN_TIME() {
        return WEEKDAY_BEGIN_TIME;
    }

    public String getWEEKDAY_END_TIME() {
        return WEEKDAY_END_TIME;
    }

    public String getWEEKEND_BEGIN_TIME() {
        return WEEKEND_BEGIN_TIME;
    }

    public String getWEEKEND_END_TIME() {
        return WEEKEND_END_TIME;
    }

    public String getHOLIDAY_BEGIN_TIME() {
        return HOLIDAY_BEGIN_TIME;
    }

    public String getHOLIDAY_END_TIME() {
        return HOLIDAY_END_TIME;
    }

    public String getSYNC_TIME() {
        return SYNC_TIME;
    }

    public String getSATURDAY_PAY_YN() {
        return SATURDAY_PAY_YN;
    }

    public String getSATURDAY_PAY_NM() {
        return SATURDAY_PAY_NM;
    }

    public String getHOLIDAY_PAY_YN() {
        return HOLIDAY_PAY_YN;
    }

    public String getHOLIDAY_PAY_NM() {
        return HOLIDAY_PAY_NM;
    }

    public String getFULLTIME_MONTHLY() {
        return FULLTIME_MONTHLY;
    }

    public String getGRP_PARKNM() {
        return GRP_PARKNM;
    }

    public String getRATES() {
        return RATES;
    }

    public String getTIME_RATE() {
        return TIME_RATE;
    }

    public String getADD_RATES() {
        return ADD_RATES;
    }

    public String getADD_TIME_RATE() {
        return ADD_TIME_RATE;
    }

    public String getBUS_RATES() {
        return BUS_RATES;
    }

    public String getBUS_TIME_RATE() {
        return BUS_TIME_RATE;
    }

    public String getBUS_ADD_TIME_RATE() {
        return BUS_ADD_TIME_RATE;
    }

    public String getBUS_ADD_RATES() {
        return BUS_ADD_RATES;
    }

    public String getDAY_MAXIMUM() {
        return DAY_MAXIMUM;
    }

    public String getLAT() {
        return LAT;
    }

    public String getLNG() {
        return LNG;
    }

    public String getASSIGN_CODE() {
        return ASSIGN_CODE;
    }

    public String getASSIGN_CODE_NM() {
        return ASSIGN_CODE_NM;
    }
}

```

### 8. api url 세팅하기

만들어놓은 POJO를 이용하여 요청을 하고 결과를 받을 api url을 세팅한다.

**ApiServices.java**

```
@GET("{start}/{end}/{location}/")
Call<GetParkInfo> getParkInfo(
        @Path("start") String start,
        @Path("end") String end,
        @Path("locatioin") String location
);

```

### 9. 레트로핏 사용하기

간단하게 row의 0번에 담겨있는 주차장의 이름을 화면에 띄워보자

![main](https://github.com/Ekutz/Retrofit-Get/blob/master/10.%20main.png?raw=true)

## 결과

![result](https://github.com/Ekutz/Retrofit-Get/blob/master/11.%20result.png?raw=true)

위와 같은 결과를 얻을 수 있다.

## 마치면서...

겨우 GET 관련 문서를 쓰면서 시간을 많이 빼앗긴것 같다. 이후에 POST 관련 글을 또 쓰겠지만 통신에 있어서 Header를 넣거나 파일을 포함하여 요청을 날리는 방법 등 다양한 통신 방식이 존재한다. GET은 가장 기본적인 방식이니 좀 더 공부해서 자유롭게 Retrofit을 써보자

[샘플 코드](https://github.com/Ekutz/Retrofit-Get-sample.git)
