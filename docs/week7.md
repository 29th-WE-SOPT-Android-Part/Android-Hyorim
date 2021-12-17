

# Week7

> onBoarding / SharedPreferences / Packaging / Navigation Component (+BackStack) / Room

- [x] Level 1
- [x] Level 2
- [ ] Level 3

| Level 1 온보딩                                               | Level 2 자동로그인                                           | Level 2 NavigationCompotent로 <br/> BackStack 관리           |
| ------------------------------------------------------------ | ------------------------------------------------------------ | ------------------------------------------------------------ |
| 앱 첫 실행시에는 온보딩이 보이고 <br/>두번째 실행부터는 보이지 않음 | 자동로그인 설정된 상태로 실행<br>환경설정에서 자동로그인 off <br>자동로그인 on은 SignInActivity에서 가능 | 세번째 화면에서 뒤로가기 누르면 <br/>첫번째 화면으로 이동    |
| <img src="https://user-images.githubusercontent.com/59546818/146475741-f9c443b8-1191-498a-93c2-446fd3a385a0.gif" width="250"> | <img src="https://user-images.githubusercontent.com/59546818/146477409-e1fa8692-5fab-424e-8606-2de266074fc5.gif" width="250"> | <img src="https://user-images.githubusercontent.com/59546818/146475721-4212d232-b89c-44d3-a52b-4893fef85f6e.gif" width="250"> |

<br>


## Level1

**Level 1-1 NavigationComponent를 활용하여 온보딩 만들기**

1. OnBardingActivity 추가 + FragmentContainer 생성
2. Fragment 3개 만들고 layout 작성
3. Navigation Component 작성 (드래그)
4. 코드단에서 button setOnClickListener
5. OnBardingActivity onCreate / onDestroy에서 SharedPreference로 온보딩 작동시키기



✔ 안드로이드 코딩을 하다보면 context를 가져와야 하는 상황이 빈번하게 등장한다.

팟짱님이 `getContext()`라는 좋은 친구를 알려줬다. 앞으로 애용해야겠다



google developer를 보니 `OnboardingSupportFragment`라는 클래스도존재한다.

초기 화면 로고 추가, 페이지 애니메이션 설정, 테마 맞춤 설정 등의 기능을 지원한다.

큰 서비스를 만들때, 온보딩을 만들어야 한다면 자세히 공부해봐야겠다.

https://developer.android.com/training/tv/playback/onboarding



**Level 1-2 SharedPreferences 활용한 AutoLogin**

1. SettingActivity추가 (자동로그인 on/off는 switch 사용)
2. Switch off : Preference remove
3. Switch on : 불가 (로그인 화면에서 설정 가능)



❓ preference를 프로퍼티로 빼야되는것 같은데, 코드를 어떻게 짜야할지 모르겠다..

✔ 자동로그인 터치영역

​	check icon에만 setOnclickListener를 설정하니, 터지 영역이 너무 작아서 불편했다.
​	이를 해결하기 위해, ConstraintLayout으로 이미지와 텍스트를 grouping하고
​	ConstraintLayout에 setOnClickListener를 달아주었다.
​	그런데 텍스트에 한해서만 onClickListenr가 작동하여 구글링 해보니
​	자식 view가 parent의 touch 부분을 intercept해서 발생하는 일이었다.

​	이전에 ViewPager2와 TabHost의 이중scroll 문제가 있었던 것과 같은 원리인듯 하다.
​	intercept를 해결하는 메소드/속성만 지정하면 쉽게 해결될 문제일것 같다.

✔PreferenceScreen

​	이전에 학교 프로젝트에서 환경설정 부분을 PreferenceScreen을 사용했던 경험이 있다.
​	나의 오만함과.. 착각이 혼합되어, 자동로그인 해제 부분에 PreferenceScreen을 적용해볼까 하는
​	생각을 잠깐 하게되었는데.. defaultPreference에는 value를 코드단에서 set이 안된다는 사실을 몰랐다.

​	현재 코드에는 prefernce를 적용한 코드는 다시 삭제하고 SOPTSharedPreferences를 통해 구현하였지만

​	그래도 PreferenceScreen에 대해 공부해 보았던 좋은 기회였다. 이에 대한 내용은 별도md 파일 입로드 할것이다.



**Level 1-3 Util class, Packaging 방식 정리**

Util class 는 아직은 많이 없고 세미나 때 배운 shortToast정도..

이제서야 코틀린이랑 아주 조금 친해져서,  불필요한 중복 코드들이 하나둘씩 눈에 들어오기 시작한다.

앱잽 시작 전까지 코드 리팩토링 하면서 util class 확장해야겠다!



Packaging 방식은 일단 크게  `data`, `ui`, `util` 이렇게 분리 했다

1. `data`

   - Data class

     나의 몇 안되는 경험상, data class는 거의 건들일이 없었다.

     또한, data 클래스와, adapter, 그리고 Activity/Fragment 의

     시작 이름이 비슷한 경우가 대부분이었기에 파일을 찾는데 다소 불편함이 있었다.

     따라서 ui와 직접적으로 관련있는 adapter, activity, fragment는 `ui`패키지로,

     남은 data 클래스는 `data` 패키지로 분리하였다.

   - SharedPreferences
     간단한 local DB인 SharedPreferences도 data 패키지에 할당했다.

2. `ui`

   - Activity / Fragment / Adapter를 포함한다

   - ui 패키지 내에서는 Activity를 기준으로 다시한번 폴더를 나눴다.

     Activity 폴더 내에는 해당 Activity에서 사용되는 Activity와 Fragment가 포함된다

   - HomeActivity의 경우 BottomNavigation을 통해 여러개의 Fragment를 포함하기에
     각 Fragment별로 또 한 번 더 패키징을 진행하였다

3. `util`

   util 클래스에는 일단 남는것을 넣어뒀다. BindingAdapter, NestedScrollableHost, ServiceCreator, Service Interface, ViewExt가 있다. 서버통신과 연관된 코드의 경우 util에 넣는게 부적절해 보이지만.. 구체적이고 마땅한 방법도 생각나지 않아 일단 묶어서 넣어뒀다.



## Level 2

**Level 2-1 NavigationComponent에서 BackStack 관리**

- popUpTo : BackStack의 어디까지 이동할지 결정하는 속성
- popUpToInclusive : popUpTo로 지정한 fragment까지 pop 시킬것인지 정하는 속성 (둘이 set인듯)

```xml
<fragment
    android:id="@+id/onBoardingFragment2"
    android:name="com.hyorim.sopt_assigmnet_1.ui.onboading.OnBoardingFragment2"
    android:label="두 번째 화면"
    tools:layout="@layout/fragment_on_boarding2">
    <action
        android:id="@+id/action_onBoardingFragment2_to_onBoardingFragment3"
        app:destination="@id/onBoardingFragment3"
        app:popUpTo="@id/onBoardingFragment1"
        app:popUpToInclusive="false"/>
</fragment>
```



**Level 2-2 NavigatioComponent와 ToolBar 연동**

간단한듯 간단하지 않은듯.. 간단해 보였는데 꽤 시간이 오래 걸렸다.

1. navigation에서 label값 변경
2. activity_onboarding.xml에서 <Toobar>추가
3. 코드단에서 navigation과 툴바 추가(이부분이 오래걸림)

```kotlin
private lateinit var navController: NavController
private lateinit var appBarConfiguration: AppBarConfiguration

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityOnboardingBinding.inflate(layoutInflater)
    setContentView(binding.root)

    setToolbarTitle()
    checkOnBoarding()
}

private fun setToolbarTitle() {
    val navHostController =
    	supportFragmentManager.findFragmentById(R.id.container_onboading) as NavHostFragment
    navController = navHostController.navController
    appBarConfiguration = AppBarConfiguration(navController.graph)

    setSupportActionBar(binding.tbOnBoarding)
    setupActionBarWithNavController(navController, appBarConfiguration)
}

```



✔가운데 정렬

Title을 가운데 정렬하고 싶어서 구글링을 많이 해봤는데, 아래와 같은 방법들만 나왔다..

그런데 이번 과제의 경우 navigation의 fragment별 label과 Toolbar를 연동시키는 것이기 때문에,

아래와 같이 하면 label 연동이 불가능 할 것이라는 직감이 들었다.

```
<Toolbar>
	<TextView>
<Toolbar>
```

뭔가 깔끔한 방법이 있을 것 같은데 정말 모르겠어서 원중 오빠 git을 들어가봤는데

`com.google.android.material.appbar.MaterialToolbar` 라는게 있었다..

해당 view를 사용하면 title 정렬 뿐만 아니라 elevation 속성도 줄 수 있다고 한다.



❓ 뒤로가기 버튼 없애기

아래의 이런 저런 방법들을 다 시도해봤는데 잘 안먹혔다.

방법을 조금 더 찾아봐야 될듯하다..!

```kotlin
binding.tbOnBoarding.navigationIcon = null
supportActionBar!!.setHomeButtonEnabled(false)
supportActionBar!!.setDisplayHomeAsUpEnabled(false)
supportActionBar!!.setDisplayShowHomeEnabled(false)
supportActionBar!!.setHomeAsUpIndicator(null)
```




(참고)

https://www.youtube.com/watch?v=28kAFPvJ5lA

https://developer.android.com/guide/navigation/navigation-ui?hl=ko
