package br.unibh.servicospessoas.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import br.unibh.servicospessoas.entidades.CEP;

public class CEPDAO implements DAO<CEP, Long> {

	


	public List<CEP> findCep(String endereco) {

		List<CEP> lista = new ArrayList<CEP>();

		try {
			PreparedStatement p = (PreparedStatement) JDBCUtil.getConnection()
					.prepareStatement("select * from tb_cep where endereco like ?");
			p.setString(1, endereco + "%");
			ResultSet res = p.executeQuery();
			while (res.next()) {
				lista.add(new CEP(res.getLong("cep"), res.getString("endereco"), res.getString("cidade")));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}

		return lista;
	}

	@Override
	public CEP find(Long cep) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement p = (PreparedStatement) JDBCUtil.getConnection()
					.prepareStatement("select * from tb_cep where cep = ?");
			p.setLong(1, cep);
			ResultSet res = p.executeQuery();

			while (res.next()) {
				return new CEP(res.getLong("cep"), res.getString("endereco"), res.getString("cidade"));
			}

		} catch (Exception e) { 
			e.printStackTrace();

		} finally {
			JDBCUtil.closeConnection();
		}

		return null;
	}

	@Override
	public void insert(CEP t) {
		// TODO Auto-generated method stub
	
		
		try {
			PreparedStatement p = (PreparedStatement) JDBCUtil.getConnection()
					.prepareStatement("insert into tb_cep (endereco,cidade) values (?,?)",Statement.RETURN_GENERATED_KEYS);

			p.setString(1, t.getEndereco());
			p.setString(2, t.getCidade());
			p.executeUpdate();
			ResultSet res = p.getGeneratedKeys();
			
			while (res.next()) {
				t.setCep((long) res.getInt(1));
               // System.out.println("Key returned from getGeneratedKeys():"
                       // + res.getInt(1));
            } 
		   
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JDBCUtil.closeConnection();
		}
		
		
	}

	@Override
	public void update(CEP t) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement p = (PreparedStatement) JDBCUtil.getConnection()
					.prepareStatement("update tb_cep set  endereco = ?, cidade = ? " + "where cep = ?");

			p.setString(1, t.getEndereco());
			p.setString(2, t.getCidade());
			p.setLong(3, t.getCep());
			p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
	}

	@Override
	public void delete(CEP t) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement p = (PreparedStatement) JDBCUtil.getConnection()
					.prepareStatement("delete from tb_cep where cep = ?");
			p.setLong(1, t.getCep());
			p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}
	}

	@Override
	public List<CEP> findAll() {
		List<CEP> lista = new ArrayList<CEP>();
		try {
			ResultSet res = JDBCUtil.getConnection().prepareStatement("SELECT * FROM tb_cep").executeQuery();

			while (res.next()) {
				lista.add(new CEP(res.getLong("cep"), res.getString("endereco"), res.getString("cidade")));
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

		}
		return lista;
	}
	
	
	public CEP findCEPl(Long cep) {
		try {
			PreparedStatement p = (PreparedStatement) JDBCUtil.getConnection()
					.prepareStatement("select * from tb_cep where cep = ?");
			p.setLong(1, cep);
			ResultSet res = p.executeQuery();
			if (res.next()) {
				return new CEP(res.getLong("cep"), res.getString("endereco"), res.getString("cidade"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}

		return null;
	}

}
