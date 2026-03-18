package Builder;

public class Main {
    public static void main(String[] args) {
        User user1 = new User.Builder("John", "John@gmail.com").build();
        System.out.println(user1);

        // Create MediatorUser with required + optional fields
        User user2 = new User.Builder("Jane", "Smith")
                .setEmail("jane@example.com")
                .setPhone("555-1234")
                .setAge(28)
                .build();
        System.out.println(user2);

        // Create MediatorUser with all fields
        User user3 = new User.Builder("Bob", "Johnson")
                .setEmail("bob@example.com")
                .setPhone("555-5678")
                .setAddress("123 Main St")
                .setAge(35)
                .build();
        System.out.println(user3);
    }
}
