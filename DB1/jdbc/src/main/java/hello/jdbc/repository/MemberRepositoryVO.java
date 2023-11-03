package hello.jdbc.repository;

import hello.jdbc.connection.DbConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.net.PortUnreachableException;
import java.sql.*;
import java.util.NoSuchElementException;


/**
 * JDBC - DriverManager 사용
 */
@Slf4j
public class MemberRepositoryVO {

    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values (? ,?)";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate();

            return member;

        } catch (SQLException e) {
            log.info("db error", e);
            throw new RuntimeException(e);
        } finally {
            close(conn, pstmt, null);
        }
    }

    public Member findById(String memberId) throws SQLException {
        String sql = "select * from member where member_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else {
                throw new NoSuchElementException("member not found memberId = " + memberId);
            }

        } catch (SQLException e) {
            log.info("error", e);
            throw e;
        } finally {
            close(conn, pstmt, rs);
        }
    }

    public void update(String memberId, int money) throws SQLException {
        String sql = "update member set money=? where member_id=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

           conn = getConnection();
           pstmt = conn.prepareStatement(sql);
           pstmt.setInt(1, money);
           pstmt.setString(2, memberId);

           int resultSize = pstmt.executeUpdate();
           log.info("resultSize = {}", resultSize);

        } catch (SQLException e) {
           log.info("error", e);
        } finally {
           close(conn, pstmt, null);
        }
    }

    public void delete(String memberId) {
        String sql = "delete from member where member_id=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {

            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
           log.info("error", e);
        } finally {
           close(conn, pstmt, null);
        }
    }


    private void close(Connection conn, Statement stmt, ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }

        // 종료는 시작 역순으로
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }

        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }

    }

    private Connection getConnection() throws SQLException {
        return DbConnectionUtil.getConnection();
    }

}
