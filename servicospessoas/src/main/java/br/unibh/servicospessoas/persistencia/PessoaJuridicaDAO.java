package br.unibh.servicospessoas.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.unibh.servicospessoas.entidades.PessoaJuridica;

public class PessoaJuridicaDAO implements DAO<PessoaJuridica, Long> {

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public PessoaJuridica find(Long id) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement p = (PreparedStatement) JDBCUtil.getConnection()
					.prepareStatement("SELECT * FROM tb_pessoa_juridica" + " WHERE id = ?");
			p.setLong(1, id);
			ResultSet res = p.executeQuery();

			while (res.next()) {
				return new PessoaJuridica(res.getLong("id"), res.getString("nome"), res.getString("endereco"),
						res.getString("telefone"), res.getString("cnpj"), res.getDate("data_constituicao"),
						res.getString("site"));
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JDBCUtil.closeConnection();
		}

		return null;
	}

	public PessoaJuridica find(String nome) {
		try {
			PreparedStatement p = (PreparedStatement) JDBCUtil.getConnection()
					.prepareStatement("select * from tb_pessoa_juridica where nome like ?");
			p.setString(1, nome + "%");
			ResultSet res = p.executeQuery();
			if (res.next()) {
				return new PessoaJuridica(res.getLong("id"), res.getString("nome"), res.getString("endereco"),
						res.getString("telefone"), res.getString("cnpj"), res.getDate("data_constituicao"),
						res.getString("site"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}

		return null;
	}
	
	public PessoaJuridica findCnpj(String cnpj) {
		try {
			PreparedStatement p = (PreparedStatement) JDBCUtil.getConnection()
					.prepareStatement("select * from tb_pessoa_juridica where cnpj = ?");
			p.setString(1, cnpj);
			ResultSet res = p.executeQuery();
			if (res.next()) {
				return new PessoaJuridica(res.getLong("id"), res.getString("nome"), res.getString("endereco"),
						res.getString("telefone"), res.getString("cnpj"), res.getDate("data_constituicao"),
						res.getString("site"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}

		return null;
	}

	@Override
	public void insert(PessoaJuridica t) {
		try {
			PreparedStatement p = (PreparedStatement) JDBCUtil.getConnection().prepareStatement(
					"insert into tb_pessoa_juridica (nome,endereco,telefone,cnpj,data_constituicao,site) values"
							+ " (?,?,?,?,?,?)");
			p.setString(1, t.getNome());
			p.setString(2, t.getEndereco());
			p.setString(3, t.getTelefone());
			p.setString(4, t.getCnpj());
			p.setString(5, df.format(t.getDataConstituicao()));
			p.setString(6, t.getSite());
			p.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			JDBCUtil.closeConnection();
		}

	}

	@Override
	public void update(PessoaJuridica t) {
		try {
			PreparedStatement p = (PreparedStatement) JDBCUtil.getConnection().prepareStatement(
					"update tb_pessoa_juridica set nome = ?, endereco = ?, telefone = ?, cnpj = ?, data_constituicao = ?, site = ? "
							+ "where id = ?");

			p.setString(1, t.getNome());
			p.setString(2, t.getEndereco());
			p.setString(3, t.getTelefone());
			p.setString(4, t.getCnpj());
			p.setString(5, df.format(t.getDataConstituicao()));
			p.setString(6, t.getSite());
			p.setLong(7, t.getId());
			p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}

	}

	@Override
	public void delete(PessoaJuridica t) {
		try {
			PreparedStatement p = (PreparedStatement) JDBCUtil.getConnection()
					.prepareStatement("delete from tb_pessoa_juridica where id = ?");
			p.setLong(1, t.getId());
			p.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.closeConnection();
		}

	}

	@Override
	public List<PessoaJuridica> findAll() {
		List<PessoaJuridica> lista = new ArrayList<PessoaJuridica>();
		try {
			ResultSet res = JDBCUtil.getConnection().prepareStatement("SELECT * FROM tb_pessoa_juridica")
					.executeQuery();

			while (res.next()) {
				lista.add(new PessoaJuridica(res.getLong("id"), res.getString("nome"), res.getString("endereco"),
						res.getString("telefone"), res.getString("cnpj"), res.getDate("data_constituicao"),
						res.getString("site")));
			}

		} catch (Exception e) {
			e.printStackTrace();

		} finally {

		}
		return lista;
	}

}
