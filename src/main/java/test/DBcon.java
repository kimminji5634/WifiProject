package test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import test.Member;
import test.DBcon;
import test.Wifi;

// 자바 이클립스에서 마리아db 연결하기!! => 접속 성공
public class DBcon {

	private static final String String = null;
	//private char[] distance;

	// WIFI SELECT문 => 20개씩 띄우기 
	public List<Wifi> list() {
		List<Wifi> wifiList = new ArrayList<>();

		String url = "jdbc:mariadb://database-2.cukcqfqrtwpx.ap-northeast-2.rds.amazonaws.com:3306/zerobase-study";
		String dbUserId = "root"; // DB 유저 아이디
		String dbPassword = "zerobase"; // DB 패스워드

		// 예외 처리 하는 방법 -> try ~ catch로 직접 예외처리 해주는 경우, throw로 예외처리 해주는 경우
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);


			String sql = " SELECT *, "
					+ " (6371*acos(cos(radians(37.5460689))*cos(radians(w.LAT))*cos(radians(w.LNT)-radians(126.7280587))+sin(radians(37.5460689))*sin(radians(w.LAT)))) as distance "
					+ " FROM WIFI w ";

			preparedStatement = connection.prepareStatement(sql); // new로 생성하지 않는다! connection에서 가져옴
			// preparedStatement.setString(1, memberTypeValue);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String manageNo = rs.getString("X_SWIFI_MGR_NO");
				String gu = rs.getString("X_SWIFI_WRDOFC");
				String wifiName = rs.getString("X_SWIFI_MAIN_NM");
				String roadAddress = rs.getString("X_SWIFI_ADRES1");
				String detailAddress = rs.getString("X_SWIFI_ADRES2");
				String floor = rs.getString("X_SWIFI_INSTL_FLOOR");
				String installType = rs.getString("X_SWIFI_INSTL_TY");
				String installAgency = rs.getString("X_SWIFI_INSTL_MBY");
				String service = rs.getString("X_SWIFI_SVC_SE");
				String net = rs.getString("X_SWIFI_CMCWR");
				String year = rs.getString("X_SWIFI_CNSTC_YEAR");
				String inOut = rs.getString("X_SWIFI_INOUT_DOOR");
				String environment = rs.getString("X_SWIFI_REMARS3");
				String date = rs.getString("WORK_DTTM");
				String lat = rs.getString("LAT");
				String lnt = rs.getString("LNT");
				String distance = rs.getString("distance");

				// 클래스 생성 추가 => Wifi클래스 set메소드와 메소드명이 일치해야 함
				Wifi wifi = new Wifi(manageNo, gu, wifiName, roadAddress, detailAddress, floor, installType,
						installAgency, service, net, year, inOut, environment, date, lat, lnt);
				wifi.setX_SWIFI_MGR_NO(manageNo);
				wifi.setX_SWIFI_WRDOFC(gu);
				wifi.setX_SWIFI_MAIN_NM(wifiName);
				wifi.setX_SWIFI_ADRES1(roadAddress);
				wifi.setX_SWIFI_ADRES2(detailAddress);
				wifi.setX_SWIFI_INSTL_FLOOR(floor);
				wifi.setX_SWIFI_INSTL_TY(installType);
				wifi.setX_SWIFI_INSTL_MBY(installAgency);
				wifi.setX_SWIFI_SVC_SE(service);
				wifi.setX_SWIFI_CMCWR(net);
				wifi.setX_SWIFI_CNSTC_YEAR(year);
				wifi.setX_SWIFI_INOUT_DOOR(inOut);
				wifi.setX_SWIFI_REMARS3(environment);
				wifi.setWORK_DTTM(date);
				wifi.setLAT(lat);
				wifi.setLNT(lnt);
				//wifi.setDistance(distance);
				wifiList.add(wifi);

				System.out.println(manageNo + ", " + gu + ", " + wifiName + ", " + roadAddress + ", " + detailAddress
						+ ", " + floor + ", " + installType + ", " + installAgency + ", " + service + ", " + net + ", "
						+ year + ", " + inOut + ", " + environment + ", " + date + ", " + lat + ", " + lnt + ", " + distance);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// try문장 처리하다가 지금 위치까지 안 올 수 있기 때문에 close 제대로 해주기 위해 위치 변경 필요
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return wifiList;
	}

	// WIFI INSERT문 => 잘 된다!! => 첫번쨰 등록
	public void register(Wifi wifi) { // 와이파이 정보 등록 

		// **값이 데이터베이스에 넣어져야함
		String url = "jdbc:mariadb://database-2.cukcqfqrtwpx.ap-northeast-2.rds.amazonaws.com:3306/zerobase-study";
		String dbUserId = "root"; // DB 유저 아이디
		String dbPassword = "zerobase"; // DB 패스워드

		// 예외 처리 하는 방법 -> try ~ catch로 직접 예외처리 해주는 경우, throw로 예외처리 해주는 경우
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);
			/*
			String sql = " insert into WIFI (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, "
					+ " X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, "
					+ " X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, "
					+ " X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, "
					+ " X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM ) "
					+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
			*/
			String sql = " insert ignore into WIFI (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, "
					+ " X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, "
					+ " X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, "
					+ " X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, "
					+ " X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM ) "
					+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
					
			
			preparedStatement = connection.prepareStatement(sql); // new로 생성하지 않는다! connection에서 가져옴
			preparedStatement.setString(1, wifi.getX_SWIFI_MGR_NO());
			preparedStatement.setString(2, wifi.getX_SWIFI_WRDOFC());
			preparedStatement.setString(3, wifi.getX_SWIFI_MAIN_NM());
			preparedStatement.setString(4, wifi.getX_SWIFI_ADRES1());
			preparedStatement.setString(5, wifi.getX_SWIFI_ADRES2());
			preparedStatement.setString(6, wifi.getX_SWIFI_INSTL_FLOOR());
			preparedStatement.setString(7, wifi.getX_SWIFI_INSTL_TY());
			preparedStatement.setString(8, wifi.getX_SWIFI_INSTL_MBY());
			preparedStatement.setString(9, wifi.getX_SWIFI_SVC_SE());
			preparedStatement.setString(10, wifi.getX_SWIFI_CMCWR());
			preparedStatement.setString(11, wifi.getX_SWIFI_CNSTC_YEAR());
			preparedStatement.setString(12, wifi.getX_SWIFI_INOUT_DOOR());
			preparedStatement.setString(13, wifi.getX_SWIFI_REMARS3());
			preparedStatement.setString(14, wifi.getLAT());
			preparedStatement.setString(15, wifi.getLNT());
			preparedStatement.setString(16, wifi.getWORK_DTTM());

			int affected = preparedStatement.executeUpdate();

			/*
			 * if (affected > 0) { System.out.println("저장 성공"); } else {
			 * System.out.println("저장 실패"); }
			 */
			 
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// try문장 처리하다가 지금 위치까지 안 올 수 있기 때문에 close 제대로 해주기 위해 위치 변경 필요
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// WIFI 데이터 업데이트 => 중복 아닌 것 insert
	public void IfNotExistRegister(Wifi wifi) { // 와이파이 정보 등록

		// **값이 데이터베이스에 넣어져야함
		String url = "jdbc:mariadb://database-2.cukcqfqrtwpx.ap-northeast-2.rds.amazonaws.com:3306/zerobase-study";
		String dbUserId = "root"; // DB 유저 아이디
		String dbPassword = "zerobase"; // DB 패스워드

		// 예외 처리 하는 방법 -> try ~ catch로 직접 예외처리 해주는 경우, throw로 예외처리 해주는 경우
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);

			String sql = " insert into WIFI (X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, "
					+ " X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, "
					+ " X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, "
					+ " X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, "
					+ " X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM ) "
					+ " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
					+ " where not exists "
					+ " (select X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, "
					+ " X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, " 
					+ " X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, " 
					+ " X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, " 
					+ " X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM "
					+ " from WIFI); ";

			preparedStatement = connection.prepareStatement(sql); // new로 생성하지 않는다! connection에서 가져옴
			preparedStatement.setString(1, wifi.getX_SWIFI_MGR_NO());
			preparedStatement.setString(2, wifi.getX_SWIFI_WRDOFC());
			preparedStatement.setString(3, wifi.getX_SWIFI_MAIN_NM());
			preparedStatement.setString(4, wifi.getX_SWIFI_ADRES1());
			preparedStatement.setString(5, wifi.getX_SWIFI_ADRES2());
			preparedStatement.setString(6, wifi.getX_SWIFI_INSTL_FLOOR());
			preparedStatement.setString(7, wifi.getX_SWIFI_INSTL_TY());
			preparedStatement.setString(8, wifi.getX_SWIFI_INSTL_MBY());
			preparedStatement.setString(9, wifi.getX_SWIFI_SVC_SE());
			preparedStatement.setString(10, wifi.getX_SWIFI_CMCWR());
			preparedStatement.setString(11, wifi.getX_SWIFI_CNSTC_YEAR());
			preparedStatement.setString(12, wifi.getX_SWIFI_INOUT_DOOR());
			preparedStatement.setString(13, wifi.getX_SWIFI_REMARS3());
			preparedStatement.setString(14, wifi.getLAT());
			preparedStatement.setString(15, wifi.getLNT());
			preparedStatement.setString(16, wifi.getWORK_DTTM());

			int affected = preparedStatement.executeUpdate();

			/*
			 * if (affected > 0) { System.out.println("저장 성공"); } else {
			 * System.out.println("저장 실패"); }
			 */
			 
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// try문장 처리하다가 지금 위치까지 안 올 수 있기 때문에 close 제대로 해주기 위해 위치 변경 필요
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// MEMBER INSERT문 => 잘 됨
	public List<Member> memberInsert() {
		List<Member> memberList = new ArrayList<>();

		String url = "jdbc:mariadb://database-2.cukcqfqrtwpx.ap-northeast-2.rds.amazonaws.com:3306/zerobase-study";
		String dbUserId = "root"; // DB 유저 아이디
		String dbPassword = "zerobase"; // DB 패스워드

		// 예외 처리 하는 방법 -> try ~ catch로 직접 예외처리 해주는 경우, throw로 예외처리 해주는 경우
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		// email, kakao, facebook 처럼 데이터 조건 바뀔 수 있음
		// String memberTypeValue = "email"; // => 이렇게만 하면 해커들이 sql injection 공격함

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);

			String sql = " insert into MEMBER (id, LAT, LNT, selectDate) "
					+ " values(?, ?, ?, ?); ";
			// " where member_type = ? ";

			preparedStatement = connection.prepareStatement(sql); // new로 생성하지 않는다! connection에서 가져옴
			// preparedStatement.setString(1, memberTypeValue);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Integer id = rs.getInt("id");
				Double lat = rs.getDouble("LAT");
				Double lnt = rs.getDouble("LNT");
				String selectDate = rs.getString("selectDate");

				// 클래스 생성 추가 => Wifi클래스 set메소드와 메소드명이 일치해야 함
				Member member = new Member();
				member.setId(id);
				member.setLAT(lat);
				member.setLNT(lnt);
				member.setSelectDate(selectDate);

				memberList.add(member);

				System.out.println(id + ", " + lat + ", " + lnt + ", " + selectDate);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// try문장 처리하다가 지금 위치까지 안 올 수 있기 때문에 close 제대로 해주기 위해 위치 변경 필요
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return memberList;
	}

	// MEMBER INSERT문 => 잘 됨
		public List<Member> memberSelect() {
			List<Member> memberList = new ArrayList<>();

			String url = "jdbc:mariadb://database-2.cukcqfqrtwpx.ap-northeast-2.rds.amazonaws.com:3306/zerobase-study";
			String dbUserId = "root"; // DB 유저 아이디
			String dbPassword = "zerobase"; // DB 패스워드

			// 예외 처리 하는 방법 -> try ~ catch로 직접 예외처리 해주는 경우, throw로 예외처리 해주는 경우
			try {
				Class.forName("org.mariadb.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;

			// email, kakao, facebook 처럼 데이터 조건 바뀔 수 있음
			// String memberTypeValue = "email"; // => 이렇게만 하면 해커들이 sql injection 공격함

			try {
				connection = DriverManager.getConnection(url, dbUserId, dbPassword);

				String sql = " select * from MEMBER; " ;
				// " where member_type = ? ";

				preparedStatement = connection.prepareStatement(sql); // new로 생성하지 않는다! connection에서 가져옴
				// preparedStatement.setString(1, memberTypeValue);

				rs = preparedStatement.executeQuery();

				while (rs.next()) {
					Integer id = rs.getInt("id");
					Double lat = rs.getDouble("LAT");
					Double lnt = rs.getDouble("LNT");
					String selectDate = rs.getString("selectDate");

					// 클래스 생성 추가 => Wifi클래스 set메소드와 메소드명이 일치해야 함
					Member member = new Member();
					member.setId(id);
					member.setLAT(lat);
					member.setLNT(lnt);
					member.setSelectDate(selectDate);

					memberList.add(member);

					System.out.println(id + ", " + lat + ", " + lnt + ", " + selectDate);
				}

			} catch (SQLException e) {
				e.printStackTrace();

			} finally {
				// try문장 처리하다가 지금 위치까지 안 올 수 있기 때문에 close 제대로 해주기 위해 위치 변경 필요
				try {
					if (rs != null && !rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					if (preparedStatement != null && !preparedStatement.isClosed()) {
						preparedStatement.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				try {
					if (connection != null && !connection.isClosed()) {
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return memberList;
		}


	// 거리 구하기 ( 내 위치 <-> 와이파이 ) => WIFI 데이터에 내 위치와의 거리도 츨력
	public List<Wifi> distance() {
		List<Wifi> wifiList = new ArrayList<>();

		String url = "jdbc:mariadb://database-2.cukcqfqrtwpx.ap-northeast-2.rds.amazonaws.com:3306/zerobase-study";
		String dbUserId = "root"; // DB 유저 아이디
		String dbPassword = "zerobase"; // DB 패스워드

		// 예외 처리 하는 방법 -> try ~ catch로 직접 예외처리 해주는 경우, throw로 예외처리 해주는 경우
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		try {
			connection = DriverManager.getConnection(url, dbUserId, dbPassword);

			String sql = " SELECT *, "
					+ " round((6371*acos(cos(radians(37.5460689))*cos(radians(w.LAT))*cos(radians(w.LNT)-radians(126.7280587))+sin(radians(37.5460689))*sin(radians(w.LAT)))),4) as distance "
					+ " FROM WIFI w "
					+ " order by distance "
					+ " limit 0, 20 ";
					//" , (select LAT2, LNT2 from WIFI) as w ";
			// " where member_type = ? ";

			preparedStatement = connection.prepareStatement(sql); // new로 생성하지 않는다! connection에서 가져옴
			// preparedStatement.setString(1, memberTypeValue);

			rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String manageNo = rs.getString("X_SWIFI_MGR_NO");
				String gu = rs.getString("X_SWIFI_WRDOFC");
				String wifiName = rs.getString("X_SWIFI_MAIN_NM");
				String roadAddress = rs.getString("X_SWIFI_ADRES1");
				String detailAddress = rs.getString("X_SWIFI_ADRES2");
				String floor = rs.getString("X_SWIFI_INSTL_FLOOR");
				String installType = rs.getString("X_SWIFI_INSTL_TY");
				String installAgency = rs.getString("X_SWIFI_INSTL_MBY");
				String service = rs.getString("X_SWIFI_SVC_SE");
				String net = rs.getString("X_SWIFI_CMCWR");
				String year = rs.getString("X_SWIFI_CNSTC_YEAR");
				String inOut = rs.getString("X_SWIFI_INOUT_DOOR");
				String environment = rs.getString("X_SWIFI_REMARS3");
				String date = rs.getString("WORK_DTTM");
				String lat = rs.getString("LAT");
				String lnt = rs.getString("LNT");
				String distance = rs.getString("distance");

				// 클래스 생성 추가 => Wifi클래스 set메소드와 메소드명이 일치해야 함
				Wifi wifi = new Wifi(manageNo, gu, wifiName, roadAddress, detailAddress, floor, installType,
						installAgency, service, net, year, inOut, environment, date, lat, lnt);
				wifi.setX_SWIFI_MGR_NO(manageNo);
				wifi.setX_SWIFI_WRDOFC(gu);
				wifi.setX_SWIFI_MAIN_NM(wifiName);
				wifi.setX_SWIFI_ADRES1(roadAddress);
				wifi.setX_SWIFI_ADRES2(detailAddress);
				wifi.setX_SWIFI_INSTL_FLOOR(floor);
				wifi.setX_SWIFI_INSTL_TY(installType);
				wifi.setX_SWIFI_INSTL_MBY(installAgency);
				wifi.setX_SWIFI_SVC_SE(service);
				wifi.setX_SWIFI_CMCWR(net);
				wifi.setX_SWIFI_CNSTC_YEAR(year);
				wifi.setX_SWIFI_INOUT_DOOR(inOut);
				wifi.setX_SWIFI_REMARS3(environment);
				wifi.setWORK_DTTM(date);
				wifi.setLAT(lat);
				wifi.setLNT(lnt);
				wifi.setDistance(distance);
				wifiList.add(wifi);

				System.out.println(manageNo + ", " + gu + ", " + wifiName + ", " + roadAddress + ", " + detailAddress
						+ ", " + floor + ", " + installType + ", " + installAgency + ", " + service + ", " + net + ", "
						+ year + ", " + inOut + ", " + environment + ", " + date + ", " + lat + ", " + lnt + "," + distance);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			// try문장 처리하다가 지금 위치까지 안 올 수 있기 때문에 close 제대로 해주기 위해 위치 변경 필요
			try {
				if (rs != null && !rs.isClosed()) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return wifiList;
	}
	
	
	public static void main(String[] args) {
		// 메소드에 static이 없으면 아래에서 클래스 객체 생성 해주어야 메소드를 쓸 수 있다
		DBcon dbCon = new DBcon();

		//dbCon.list2(); // => 마리아 디비 연결한 DBeaver에서 쿼리에 값을 넣고 여기서 이렇게 확인하면 콘솔에 잘 뜬다!
		//dbCon.list(); // => 잘 됨
		//dbCon.distance(); //=> 잘 됨
		dbCon.memberInsert();
		
		
		

	}
}