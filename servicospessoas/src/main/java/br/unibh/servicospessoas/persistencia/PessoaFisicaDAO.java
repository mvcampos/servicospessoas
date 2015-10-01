package br.unibh.servicospessoas.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.unibh.servicospessoas.entidades.PessoaFisica;

public class PessoaFisicaDAO implements DAO<PessoaFisica, Long> {

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public PessoaFisica find(Long id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement p = (PreparedStatement) JDBCUtil.getConnection()
					.prepareStatement("SELECT * FROM tb_pessoa_fisica" + " WHERE id = ?");
			p.setLong(1, id);
			ResultSet res = p.executeQuery();

			while (res.next()) {
				return new PessoaFisica(res.getLong("id"), res.getString("nome"), res.getString("endereco"),
						res.getString("telefone"), res.getString("cpf"), res.getString("email"),
						res.getDate("data_nascimento"), res.getString("sexo"));
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JDBCUtil.closeConnection();
		}

		return null;
	}

	public PessoaFisica find(String nome) {
		try {
			PreparedStatement p = (PreparedStatement) JDBCUtil.getConnection()
					.prepareStatement("select * from tb_pessoa_fisica where nome like ?");
			p.setString(1, nome + "%");
			ResultSet res = p.executeQuery();
			if (res.next()) {
				return new PessoaFisica(res.getLong("id"), res.getString("nome"), res.getString("endereco"),
						res.getString("telefone"), res.getString("cpf"), res.getString("email"),
						res.getDate("data_nascimento"), res.getString("sexo"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}

		return null;
	}
	
	public PessoaFisica findCpf(String cpf) {
		try {
			PreparedStatement p = (PreparedStatement) JDBCUtil.getConnection()
					.prepareStatement("select * from tb_pessoa_fisica where cpf = ?");
			p.setString(1, cpf);
			ResultSet res = p.executeQuery();
			if (res.next()) {
				return new PessoaFisica(res.getLong("id"), res.getString("nome"), res.getString("endereco"),
						res.getString("telefone"), res.getString("cpf"), res.getString("email"),
						res.getDate("data_nascimento"), res.getString("sexo"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}

		return null;
	}

	@Override
	public void insert(PessoaFisica t) {

		try {
			PreparedStatement p = (PreparedStatement) JDBCUtil.getConnection().prepareStatement(
					"insert into tb_pessoa_fisica (nome,endereco,telefone,cpf,email,data_nascimento,sexo) values"
							+ " (?,?,?,?,?,?,?)");
			p.setString(1, t.getNome());
			p.setString(2, t.getEndereco());
			p.setString(3, t.getTelefone());
			p.setString(4, t.getCpf());
			p.setString(5, t.getEmail());
			p.setString(6, df.format(t.getDataNascimento()));
			p.setString(7, t.getSexo());
			p.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JDBCUtil.closeConnection();
		}

	}

	@Override
	public void update(PessoaFisica t) {

		try {
			PreparedStatement p = (PreparedStatement) JDBCUtil.getConnection().prepareStatement(
					"update tb_pessoa_fisica set nome = ?, endereco = ?, telefone = ?, cpf = ?, email = ?, data_nascimento = ?, sexo = ? "
							+ "where id = ?");

			p.setString(1, t.getNome());
			p.setString(2, t.getEndereco());
			p.setString(3, t.getTelefone());
			p.setString(4, t.getCpf());
			p.setString(5, t.getEmail());
			p.setString(6, df.format(t.getDataNascimento()));
			p.setString(7, t.getSexo());
			p.setLong(8, t.getId());
			p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}

	}

	@Override
	public void delete(PessoaFisica t) {

		try {
			PreparedStatement p = (PreparedStatement) JDBCUtil.getConnection()
					.prepareStatement("delete from tb_pessoa_fisica where id = ?");
			p.setLong(1, t.getId());
			p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}

	}

	@Override
	public List<PessoaFisica> findAll() {
		List<PessoaFisica> lista = new ArrayList<PessoaFisica>();
		try {
			ResultSet res = JDBCUtil.getConnection().prepareStatement("SELECT * FROM tb_pessoa_fisica").executeQuery();

			while (res.next()) {
				lista.add(new PessoaFisica(res.getLong("id"), res.getString("nome"), res.getString("endereco"),
						res.getString("telefone"), res.getString("cpf"), res.getString("email"),
						res.getDate("data_nascimento"), res.getString("sexo")));
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

		}
		return lista;
	}

}
