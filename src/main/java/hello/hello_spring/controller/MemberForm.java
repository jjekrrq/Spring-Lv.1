package hello.hello_spring.controller;

// createMembersForm.html에서 <form></form>태그으; name 속성의 값과 MemberForm 클래스의 name필드의
// 이름을 일치시킨다면, 해당 <form>태그의 action방식(post, get ..)을 통해 들어온다.
public class MemberForm {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
