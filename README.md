# java-project Domarket
![gui](https://github.com/user-attachments/assets/b2ab092f-7d36-4df5-8099-45c62a988f6b)


## 프로젝트 기간:2024.04.15~2024.04.22  
## 개발인원 : 1명
## 🛠️ Stack
    
![java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![oracle](https://img.shields.io/badge/Oracle-F80000?style=for-the-badge&logo=oracle&logoColor=black)
![eclipse](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)
![sqldeveloper](https://img.shields.io/badge/sqldeveloper-%235391FE.svg?style=for-the-badge&logo=sqldeveloper&logoColor=white)
   
##  프로젝트 개요  

- Java GUI 및 JDBC 오라클 연동 을 활용하여 마켓 프로그램 을 구현하였습니다 .
- 관리자는 로그인후  상품 등록, 입고, 삭제, 전체보기 기능을 통해 상품을 관리할 수 있습니다.
- 사용자는 회원가입 및 로그인후 장바구니 담기, 장바구니 취소, 장바구니 보기(결제기능), 개인 정보 보기(보유금액 충전기능) 을 통해 편리하게 상품을 구매할수있습니다.</li></li></div> 
  
  [DoMarket_PDF](https://github.com/doyoungking/java-project/blob/main/java_project_DoMarket.pdf)

  
## 주요 기능 
- dao package
  * DB연동 class 묶음(jdbc 드라이버 활용하여 DB연결)
- dto package
  * 정보 저장 객체 class 묶음(주소를 주입하는방식으로 매개변수 넘기기 위해 객체 활용)
- Main
  * 프로그램 실행 class
- mgui package
  * 관리자 메뉴 class 묶음(관리자화면,상품등록,상품입고,상품삭제,전체보기)
- ugui package
  * 사용자 메뉴 class 묶음(사용자화면,장바구니담기,장바구니 취소,장바구니 보기(결제),개인정보 보기(금액충전))
-----------------------------------
- swing package를 이용하여 gui 구현
  * Jframe 을 상속받거나 직접 Jtable등 객체를 생성하여 gui를 구현했다
- awt package를 이용하여 GUI 에서 발생하는 이벤트를 처리 
  * ActionListener 인터페이스 구현을 통해 method 재정의하여 마우스 클릭 Event 처리 코드 구현.

  
  
## 배운점

 + 순수 객체 지향언어 기반 프로젝트 를 진행하면서 객체 생성 및 소멸을 이해하며 상속,캡슐화 같은 객체지향언어의 특징을 이해하고 활용 할수 있었습니다.
 + 객체지향언어 인 java 를 통해서 javascript 등 다른 언어를 배울 때 쉽게 접근 할수 있는 기반을 만들수 있었습니다.
 + oracle JDBC 를 활용하여 db연결을 하였는데 , CRUD 쿼리문을 작성하면서 데이터 조작에대한 이해를 높일수 있었습니다.

    
