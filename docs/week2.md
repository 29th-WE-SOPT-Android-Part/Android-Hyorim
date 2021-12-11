## Level 1. 필수과제

[![ezgif com-gif-maker](https://user-images.githubusercontent.com/59546818/138473696-86aea308-2b91-4737-bbe6-ad33312304cd.gif)](https://user-images.githubusercontent.com/59546818/138473696-86aea308-2b91-4737-bbe6-ad33312304cd.gif)

1. activity_home.xml 하단에 FragmentContainerView 추가
2. FollowerFragment.kt 와 fragment_follwer.xml 생성
3. RepositoryFragment.kt와 fragment_repository.xml 생성
4. fragment_follwer.xml와 fragment_repository.xml 안에 들어갈 Recyclerview 생성
   1. layoutManager로 Linear / Grid 선택 가능
   2. grid의 경우 spanCount로 가로로 들어갈 항목 개수 지정
   3. listitem 에 연결시키고자 하는 list 지정
5. follower_list.xml과 repository_list.xml 생성
6. follower / repository data class 만들기
7. 각 list에 대한 Adapter 생성(ViewHolder도 여기에 같이 생성)
8. 각 RecyclerView에 Adapter 연결
9. FollowerFragment와 RepositoryFragment에서 데이터 갱신하기

- HomeActivity.kt

```
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        
        // 생략
    
        initTransactionEvent()
    
    }
    
    private fun initTransactionEvent() {
        val followerFragment = FollowerFragment()
        val repositoryFragment = RepositoryFragment()

        // Start with default fragment (FollowerFragment)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, followerFragment)
            .commit()

        // when click event happens
        setFollowerFragment(followerFragment)
        setRepositoryFragment(repositoryFragment)
    }

    private fun setFollowerFragment(followerFragment : Fragment) {
        binding.followerBtn.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, followerFragment)
            transaction.commit()
        }
    }

    private fun setRepositoryFragment(repositoryFragment: Fragment) {
        binding.repositoryBtn.setOnClickListener {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, repositoryFragment)
            transaction.commit()
        }
    }
```