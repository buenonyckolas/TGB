package tgb.lab2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AlteraAgenda {

	public AlteraAgenda() {

	}

	public void carregarAgenda(String email) {

		try {
			FileReader fr = new FileReader(email);
			BufferedReader in = new BufferedReader(fr);
			String line = in.readLine();
			while (line != null) {
				System.out.println(line);
				line = in.readLine();
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado.");
		} catch (IOException e) {
			System.out.println("Erro ao ler arquivo.");
		}

	}

	public void cadastrarNovoCompromisso(String desc, String data, String inicio, int duracao, String participantes) {

		String datas[] = data.split("/");
		VisualizaAgenda v = new VisualizaAgenda();
		v.visualizarData(Integer.parseInt(datas[0]), Integer.parseInt(datas[1]), Integer.parseInt(datas[2]));
	}

	public void cancelarCompromisso(String data, String inicio) {

		String datas[] = data.split("/");
		VisualizaAgenda v = new VisualizaAgenda();
		v.visualizarData(Integer.parseInt(datas[0]), Integer.parseInt(datas[1]), Integer.parseInt(datas[2]));

	}

	public void salvarAgenda() {

	}
}
