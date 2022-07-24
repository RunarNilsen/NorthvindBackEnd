package kodlamaio.northwind.business.concretes;

import java.util.Optional;
import java.util.logging.Logger;

import kodlamaio.northwind.core.entities.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.dataAccess.UserDao;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.ErrorDataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.core.utilities.results.SuccessResult;

@Service
public class UserManager implements UserService {
	private UserDao userDao;

	Logger logger =  java.util.logging.Logger.getLogger(this.getClass().getName());

	@Autowired
	public UserManager(final UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public Result add(final User user) {
		userDao.save(user);

		return new SuccessResult("User has been added.");
	}

	@Override
	public DataResult<User> getByEmail(final String email) {
		final Optional<User> user = userDao.findByEmail(email);

		if (user.isEmpty())
			return new ErrorDataResult<User>("User not found.");

		return new SuccessDataResult<User>(user.get());
	}
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		Optional<User> user = userDao.findByUserName(userName);
		System.out.println("HERE i user: " + user.toString());
		logger.info("This is an info message HERE i user:" + user.toString() );

		user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));

		return user.map(MyUserDetails::new).get();
	}

}
