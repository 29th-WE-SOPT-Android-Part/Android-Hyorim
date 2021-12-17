## PreferenceScreen 시행착오



예전에 학교에서 프로젝트를 할 때 SettingActivity를 Preference Screen을 사용하여 만든적이 있었다.

이름에서 유추할 수 있듯, 이 기능 또한 SharedPreference를 사용한다.

이것을 사용하여 그나마 layout을 조금 더 쉽게 구성하고,

코드단에서 SOPTSharedPreference와 getDefaultSharedPreferences를 살짝 연동만 시켜주면

쉽게 구현할 수 있지 않을까 라는 생각이 대참사를 불러 일으켰다😂

그래도 PreferenceScreen 사용하는 방법을 공부했으니, 언젠간 쓰이겠지 마음으로 간단히 정리만 해두려고 한다!



구현과정은 아래와 같다

0. dependency 추가   `androidx.preference:preference:1.1.0`

1. **계층 구조 만들기**

   - activity_setting.xml 만들고 FragmentContainerView 생성 

   ```kotlin
   <androidx.fragment.app.FragmentContainerView
       android:id="@+id/fcv_setting"
       android:layout_width="match_parent"
       android:layout_height="0dp"
       app:layout_constraintBottom_toBottomOf="parent"
       app:layout_constraintTop_toBottomOf="@id/view_grey_line" />
   ```

   - drawable/xml/ 레벨에 xml 파일 생성 (preference_setting.xml)

   ```xml
   <PreferenceScreen xmlns:android="http://schemas.android.com/apk/res/android"
       xmlns:app="http://schemas.android.com/apk/res-auto">
   
       <SwitchPreferenceCompat
           android:title="@string/auto_login"
           app:iconSpaceReserved="false"
           app:key="autoLogin" />
   
   </PreferenceScreen>
   ```

   

2. **계층 구조 확장**

   - SettingFragment.kt 생성

     XML 특성에서 계층 구조를 확장하려면 [`PreferenceFragmentCompat`](https://developer.android.com/reference/androidx/preference/PreferenceFragmentCompat)를 만들고,

     [`onCreatePreferences()`](https://developer.android.com/reference/androidx/preference/PreferenceFragmentCompat#oncreatepreferences)를 재정의하여 확장하기 위한 XML 리소스를 제공하세요. 

   ```kotlin
   class SettingFragment : PreferenceFragmentCompat() {
       override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
           setPreferencesFromResource(R.xml.preference_setting, rootKey)
       }
   }
   ```

   - 그런 다음 다른 `Fragment`를 사용할 때와 마찬가지로 이 `Fragment`를 `Activity`로 추가할 수 있습니다.

   ```kotlin
   supportFragmentManager
       .beginTransaction()
       .replace(R.id.fcv_setting, SettingFragment())
       .commit()
   ```



코드단에서 preference Screen을 사용하기 위해서는 아래의 코드를 활용하면 된다.

   ```kotlin
   val settingPref = PreferenceManager.getDefaultSharedPreferences(this)
   val isAutoLogin = settingPref.getBoolean("autoLogin", false)
   ```

   

(참고 자료)

https://developer.android.com/guide/topics/ui/settings

https://codechacha.com/ko/android-preference/



