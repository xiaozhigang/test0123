package travel.dao.impl;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import travel.dao.UserDao;
import travel.domain.User;
import travel.util.JDBCUtils;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findByUsername(String userName) {

        String sql = "select * from user where userNam = ?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), userName);
        return user;
    }

    @Override
    public void save(User user) {

        String sql = "insert into user(userName, password, name, birthday, sex, telephone, email, status, code) values(?,?,?,?,?,?,?,?,?)";
        template.update(sql,user.getUserName(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail(), user.getStatus(), user.getCode()
        );
    }

    @Override
    public User findByCode(String code) {

        String sql = "select * from user where code = ?";
        User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        return user;
    }

    @Override
    public void updateStatus(User user) {
        String sql = "update user set status = 'Y' where uid = ?";
        template.update(sql, user.getUid());
    }

    @Override
    public User findByUsernameAndPassword(String userName, String password) {
        String sql = "select * from user where userName = ? and password = ?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), userName, password);
    }
}
