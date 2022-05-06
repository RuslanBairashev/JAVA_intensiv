package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class ProductsRepositoryJdbcImpl implements ProductRepository {

    private static Connection connection;

    public ProductsRepositoryJdbcImpl(DataSource dataSource) {
        try { connection = dataSource.getConnection(); }
        catch (SQLException e) { e.printStackTrace(); } }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) return null;
            Product tmp;
            do {
                tmp = new Product();
                tmp.setId(rs.getLong("id"));
                tmp.setName(rs.getString("name"));
                tmp.setPrice(rs.getInt("price"));
                products.add(tmp);
            } while (rs.next());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return products;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Product product = new Product();
        String sql = "SELECT * FROM product WHERE id=?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement( sql );
            pst.setLong(1, id);
            ResultSet rs = pst.executeQuery();
            if (!rs.next()) {
                return Optional.empty();}
            product.setId(rs.getLong("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getInt("price"));
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
        return Optional.ofNullable(product);
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE product SET name=?, price=? WHERE id=?";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement( sql );
            pst.setString(1, product.getName());
            pst.setInt(2, product.getPrice());
            pst.setLong(3, product.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO product ( id, name, price) values(?,?,?)";
        PreparedStatement pst = null;
        try {
            pst = connection.prepareStatement( sql);
            pst.setLong(1, product.getId());
            pst.setString(2, product.getName());
            pst.setInt(3, product.getPrice());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM product WHERE id=?";
        PreparedStatement pst = null;
        Product tmp = new Product();
        try {
            pst = connection.prepareStatement( sql);
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
