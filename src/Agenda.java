import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.*;

public class Agenda implements IAgenda {
	
	DateFormat df = new SimpleDateFormat("dd/MM");
	
	public Agenda() {
		
		
		
	}
	
	public void carregarAgenda(String emailUsuario) throws IOException {
		try {
			FileReader fr = new FileReader(emailUsuario+".txt");
			BufferedReader in = new BufferedReader(fr);
			String line = in.readLine();
			while (line != null) {
				System.out.println(line);
				line = in.readLine();
			}
			String txt[] = line.split("/");
			for(int i = 0; i < txt.length; i++) {
				System.out.println(txt[i]);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo n�o encontrado.");
		} catch (IOException e) {
			System.out.println("Erro ao ler arquivo.");
		}
		
	}
	
	public void visualizarSemana() {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(calendar.SUNDAY);
		int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);
		calendar.add(Calendar.DAY_OF_MONTH, Calendar.SUNDAY - diaSemana);
		System.out.println(df.format(calendar.getTime()));
	}
	
	public void visualizarDiaSemana(int diaSemana) {
		
	}
	
	public void visualizarData(int ano, int mes, int dia) {
		
	}
	
	public void novoCompromisso(String descr, int ano, int mes, int dia,
	int hora, int min, String[] partic) throws AgendaException {
		
		
		
	}

	
	public void salvar() throws IOException {
		
	}
	
	//  M�todo louco
	
	public void cancelarCompromisso(String data, String inicio) {

		String datas[] = data.split("/");
		//VisualizaAgenda v = new VisualizaAgenda();
		//v.visualizarData(Integer.parseInt(datas[0]), Integer.parseInt(datas[1]), Integer.parseInt(datas[2]));

	}
	
}