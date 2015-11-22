import java.io.*;

public interface IAgenda {

	public void carregarAgenda(String emailUsuario) throws IOException;
	public void visualizarSemana();
	public void visualizarDiaSemana(int diaSemana);
	public void visualizarData(int ano, int mes, int dia);
	public void novoCompromisso(String descr, int ano, int mes, int dia,
	int hora, int min, int duracao, String[] partic) throws AgendaException;
	public void salvar() throws IOException;

}
