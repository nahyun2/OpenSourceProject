// package com.main.demo.repository;

// import com.main.demo.domain.Member;
// import com.main.demo.service.MemberService;
// import org.springframework.jdbc.datasource.DataSourceUtils;

// import javax.sql.DataSource;
// import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// public class JdbcMemberRepository implements MemberRepository {

//     private final DataSource dataSource;

//     public JdbcMemberRepository(DataSource dataSource) {
//         this.dataSource = dataSource;
//     }

//     @Override
//     public Member save(Member member) {
//         String sql = "INSERT INTO member(name) VALUES(?)";
//         try (Connection conn = getConnection();
//              PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

//             pstmt.setString(1, member.getName());
//             pstmt.executeUpdate();

//             try (ResultSet rs = pstmt.getGeneratedKeys()) {
//                 if (rs.next()) {
//                     member.setId(rs.getLong(1));
//                 } else {
//                     throw new SQLException("ID 조회 실패");
//                 }
//             }

//             return member;
//         } catch (Exception e) {
//             throw new IllegalStateException(e);
//         }
//     }

//     @Override
//     public Optional<Member> findById(Long id) {
//         String sql = "SELECT * FROM member WHERE id = ?";
//         try (Connection conn = getConnection();
//              PreparedStatement pstmt = conn.prepareStatement(sql)) {

//             pstmt.setLong(1, id);
//             try (ResultSet rs = pstmt.executeQuery()) {
//                 if (rs.next()) {
//                     Member member = new Member();
//                     member.setId(rs.getLong("id"));
//                     member.setName(rs.getString("name"));
//                     return Optional.of(member);
//                 }
//                 return Optional.empty();
//             }
//         } catch (Exception e) {
//             throw new IllegalStateException(e);
//         }
//     }

//     @Override
//     public List<Member> findAll() {
//         String sql = "SELECT * FROM member";
//         List<Member> members = new ArrayList<>();
//         try (Connection conn = getConnection();
//              PreparedStatement pstmt = conn.prepareStatement(sql);
//              ResultSet rs = pstmt.executeQuery()) {

//             while (rs.next()) {
//                 Member member = new Member();
//                 member.setId(rs.getLong("id"));
//                 member.setName(rs.getString("name"));
//                 members.add(member);
//             }
//         } catch (Exception e) {
//             throw new IllegalStateException(e);
//         }
//         return members;
//     }

//     @Override
//     public Optional<Member> findByName(String name) {
//         String sql = "SELECT * FROM member WHERE name = ?";
//         try (Connection conn = getConnection();
//              PreparedStatement pstmt = conn.prepareStatement(sql)) {

//             pstmt.setString(1, name);
//             try (ResultSet rs = pstmt.executeQuery()) {
//                 if (rs.next()) {
//                     Member member = new Member();
//                     member.setId(rs.getLong("id"));
//                     member.setName(rs.getString("name"));
//                     return Optional.of(member);
//                 }
//                 return Optional.empty();
//             }
//         } catch (Exception e) {
//             throw new IllegalStateException(e);
//         }
//     }

//     private Connection getConnection() {
//         return DataSourceUtils.getConnection(dataSource);
//     }

//     private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
//         try {
//             if (rs != null) rs.close();
//             if (pstmt != null) pstmt.close();
//             if (conn != null) close(conn);
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     private void close(Connection conn) throws SQLException {
//         DataSourceUtils.releaseConnection(conn, dataSource);
//     }
// }
