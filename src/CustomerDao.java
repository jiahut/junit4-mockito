public class CustomerDao {

    public boolean save(Customer customer) {
        return true;
    }

    public boolean exists(String phone) {
        return false;

    }

    public void updateEmail(Customer customer, String email) {
        customer.setPhone(email);
    }

    public void delete(Customer customer) {
    }
}


