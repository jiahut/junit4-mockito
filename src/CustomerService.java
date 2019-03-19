import com.google.inject.Inject;

public class CustomerService {

    @Inject
    private CustomerDao customerDao;

    public boolean addCustomer(Customer customer) {
        if (customerDao.exists(customer.getPhone())) {
            return false;
        }
        return customerDao.save(customer);
    }

    public Customer changeEmail(String oldEmail, String newEmail) {
        Customer customer = new Customer();
        customerDao.updateEmail(customer, newEmail);
        return customer;
    }

}