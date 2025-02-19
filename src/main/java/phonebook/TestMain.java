package phonebook;



public class TestMain {

	public static void main(String[] args) {
		System.out.println(new PhonebookService().pageList(2).getTotalPage());

	}

}
