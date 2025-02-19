package moa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SomeDatabaseService {

    @Autowired
    private gogoDAO repository;

    public int saveToDatabase(gogoVO vo, int userId,double price) {
        // This assumes that the user_id is known and is passed as parameter.
        // Saving product to the database and returning the number of affected rows.
        return repository.insertProductAndPriceHistory(vo, userId,price);
    }

    public int savePriceHistory(gogoVO gogo, int userId, double price) {
        // Inserting price history and returning the number of affected rows.
        return repository.insertProductAndPriceHistory(gogo,userId, price);
    }

	public int moaJoinMember(UserVO vo) {
        // Registering a new user and returning the number of affected rows.
		return repository.registerUser(vo);
	}
	
	public boolean moaLoginChk(String username, String password) {
        // Checking if the user credentials are valid and returning the result.
		return repository.loginCheck(username, password);
	}

	public int getUserIdByUsername(String username) {
		// TODO Auto-generated method stub
		return repository.getUserIdByUsername(username);
	}

	public List<Product> getProductsByUserId(int id) {
		// TODO Auto-generated method stub
		return repository.getProductsByUserId(id);
	}

	public PriceData getPriceDataForItem(int itemId) {
		// TODO Auto-generated method stub
		return repository.getPriceDataForItem(itemId);
	}
}
