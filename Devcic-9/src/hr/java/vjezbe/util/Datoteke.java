package hr.java.vjezbe.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import hr.java.vjezbe.entitet.Artikl;
import hr.java.vjezbe.entitet.Automobil;
import hr.java.vjezbe.entitet.Kategorija;
import hr.java.vjezbe.entitet.Korisnik;
import hr.java.vjezbe.entitet.PoslovniKorisnik;
import hr.java.vjezbe.entitet.PrivatniKorisnik;
import hr.java.vjezbe.entitet.Prodaja;
import hr.java.vjezbe.entitet.Stan;
import hr.java.vjezbe.entitet.Stanje;
import hr.java.vjezbe.entitet.Usluga;

public class Datoteke {
    private static final String FORMAT_DATUMA = "dd.MM.yyyy.";
    private static final String FILE_KORISNICI = "dat/korisnici.txt";
    private static final String FILE_ARTIKLI = "dat/artikli.txt";
    private static final String FILE_KATEGORIJE = "dat/kategorije.txt";
    private static final String FILE_PRODAJE = "dat/prodaje.txt";

    private static final int BROJ_LINIJA_KATEGORIJE = 3;
    private static final int BROJ_LINIJA_PRODAJE = 4;

//    List<Korisnik> listaKorisnika = new ArrayList<>();
//    List<Artikl> listaArtikala = new ArrayList<>();
//    List<Kategorija<?>> listaKategorija = new ArrayList<>();
//    List<Prodaja> listaProdaja = new ArrayList<>();
//
//    listaArtikala = dohvatiArtikle();

    public static List<Artikl> dohvatiArtikle() {

	List<String> stringListaArtikala = new ArrayList<>();
	List<Artikl> listaArtikala = new ArrayList<>();

	Long idUsluge = null;
	String naslovUsluge = null;
	String opisUsluge = null;
	Stanje stanjeUsluge = null;
	BigDecimal cijenaUsluge = null;

	Long idAutomobila = null;
	String naslovAutomobila = null;
	String opisAutomobila = null;
	BigDecimal snagaKsAutomobila = null;
	Stanje stanjeAutomobila = null;
	BigDecimal cijenaAutomobila = null;

	Long idStana = null;
	String naslovStana = null;
	String opisStana = null;
	int kvadratura = 0;
	Stanje stanjeStana = null;
	BigDecimal cijenaStana = null;

	try (Stream<String> stream = Files.lines(new File(FILE_ARTIKLI).toPath(), Charset.forName("ISO-8859-2"))) {
	    stringListaArtikala = stream.collect(Collectors.toList());
	} catch (IOException e) {
	    e.printStackTrace();
	}

	int x = 0;
	for (int j = x; j < stringListaArtikala.size(); j++) {
	    if (x < stringListaArtikala.size()) {
		Integer tipArtikla = Integer.parseInt(stringListaArtikala.get(0 + x));
		if (tipArtikla == 1) {
		    for (int i = x; i < x + 6; i++) {
			if (i == x + 1) {
			    idUsluge = Long.parseLong(stringListaArtikala.get(i));
			} else if (i == x + 2) {
			    naslovUsluge = stringListaArtikala.get(i);
			} else if (i == x + 3) {
			    opisUsluge = stringListaArtikala.get(i);
			} else if (i == x + 4) {
			    stanjeUsluge = Stanje.fromInteger(Integer.parseInt(stringListaArtikala.get(i)));
			} else if (i == x + 5) {
			    cijenaUsluge = new BigDecimal(stringListaArtikala.get(i));
			}
		    }
		    listaArtikala.add(new Usluga(idUsluge, naslovUsluge, opisUsluge, stanjeUsluge, cijenaUsluge));
		    x += 6;
		} else if (tipArtikla == 2) {
		    for (int i = x; i < x + 7; i++) {
			if (i == x + 1) {
			    idAutomobila = Long.parseLong(stringListaArtikala.get(i));
			} else if (i == x + 2) {
			    naslovAutomobila = stringListaArtikala.get(i);
			} else if (i == x + 3) {
			    opisAutomobila = stringListaArtikala.get(i);
			} else if (i == x + 4) {
			    snagaKsAutomobila = new BigDecimal(stringListaArtikala.get(i));
			} else if (i == x + 5) {
			    stanjeAutomobila = Stanje.fromInteger(Integer.parseInt(stringListaArtikala.get(i)));
			} else if (i == x + 6) {
			    cijenaAutomobila = new BigDecimal(stringListaArtikala.get(i));
			}
		    }
		    listaArtikala.add(new Automobil(idAutomobila, naslovAutomobila, opisAutomobila, snagaKsAutomobila,
			    stanjeAutomobila, cijenaAutomobila));
		    x += 7;
		} else if (tipArtikla == 3) {
		    for (int i = x; i < x + 7; i++) {
			if (i == x + 1) {
			    idStana = Long.parseLong(stringListaArtikala.get(i));
			} else if (i == x + 2) {
			    naslovStana = stringListaArtikala.get(i);
			} else if (i == x + 3) {
			    opisStana = stringListaArtikala.get(i);
			} else if (i == x + 4) {
			    kvadratura = Integer.parseInt(stringListaArtikala.get(i));
			} else if (i == x + 5) {
			    stanjeStana = Stanje.fromInteger(Integer.parseInt(stringListaArtikala.get(i)));
			} else if (i == x + 6) {
			    cijenaStana = new BigDecimal(stringListaArtikala.get(i));
			}
		    }
		    listaArtikala.add(new Stan(idStana, naslovStana, opisStana, kvadratura, stanjeStana, cijenaStana));
		    x += 7;
		}
	    } else {
		break;
	    }
	}
	return listaArtikala;

    }

    public static List<Korisnik> dohvatiKorisnike() {
	List<String> stringListaKorisnika = new ArrayList<>();
	List<Korisnik> listaKorisnika = new ArrayList<>();

	Long idPrivatniKorisnik = null;
	String imePrivatniKorisnik = null;
	String prezimePrivatniKorisnik = null;
	String emailPrivatniKorisnik = null;
	String telefonPrivatniKorisnik = null;

	Long idPoslovniKorisnik = null;
	String nazivPoslovniKorisnik = null;
	String webPoslovniKorisnik = null;
	String emailPoslovniKorisnik = null;
	String telefonPoslovniKorisnik = null;

	try (BufferedReader bufferedCitac = new BufferedReader(new FileReader(FILE_KORISNICI));) {
//	    FileReader citac = new FileReader(FILE_PRIVATNI_KORISNICI);
//	    BufferedReader bufferedCitac = new BufferedReader(citac);
	    String linija;
	    while ((linija = bufferedCitac.readLine()) != null) {
		stringListaKorisnika.add(linija);
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	}
	int x = 0;
	for (int j = x; j < stringListaKorisnika.size(); j++) {
	    if (x < stringListaKorisnika.size()) {
		Integer tipKorisnika = Integer.parseInt(stringListaKorisnika.get(0 + x));
		if (tipKorisnika == 1) {
		    for (int i = x; i < x + 6; i++) {
			if (i == x + 1) {
			    idPrivatniKorisnik = Long.parseLong(stringListaKorisnika.get(i));
			} else if (i == x + 2) {
			    imePrivatniKorisnik = stringListaKorisnika.get(i);
			} else if (i == x + 3) {
			    prezimePrivatniKorisnik = stringListaKorisnika.get(i);
			} else if (i == x + 4) {
			    emailPrivatniKorisnik = stringListaKorisnika.get(i);
			} else if (i == x + 5) {
			    telefonPrivatniKorisnik = stringListaKorisnika.get(i);
			}
		    }
		    listaKorisnika.add(new PrivatniKorisnik(idPrivatniKorisnik, imePrivatniKorisnik,
			    prezimePrivatniKorisnik, emailPrivatniKorisnik, telefonPrivatniKorisnik));
		    x += 6;
		} else if (tipKorisnika == 2) {
		    for (int i = x; i < x + 6; i++) {
			if (i == x + 1) {
			    idPoslovniKorisnik = Long.parseLong(stringListaKorisnika.get(i));
			} else if (i == x + 2) {
			    nazivPoslovniKorisnik = stringListaKorisnika.get(i);
			} else if (i == x + 3) {
			    webPoslovniKorisnik = stringListaKorisnika.get(i);
			} else if (i == x + 4) {
			    emailPoslovniKorisnik = stringListaKorisnika.get(i);
			} else if (i == x + 5) {
			    telefonPoslovniKorisnik = stringListaKorisnika.get(i);
			}
		    }
		    listaKorisnika.add(new PoslovniKorisnik(idPoslovniKorisnik, nazivPoslovniKorisnik,
			    webPoslovniKorisnik, emailPoslovniKorisnik, telefonPoslovniKorisnik));
		    x += 6;
		}

	    } else {
		break;
	    }
	}
	return listaKorisnika;
    }

    public static void zapisiUDatotekuArtikl(List<Artikl> listaArtikala) {
	String tekst = "";
	Artikl artikl = listaArtikala.get(listaArtikala.size() - 1);
	if (artikl instanceof Automobil) {
	    tekst += "\n2\n";
	    tekst += artikl.getId().toString() + "\n";
	    tekst += artikl.getNaslov() + "\n";
	    tekst += artikl.getOpis() + "\n";
	    tekst += ((Automobil) artikl).getSnagaKs().toString() + "\n";
	    tekst += Stanje.fromStanje(artikl.getStanje()) + "\n";
	    tekst += artikl.getCijena().toString();
	    try (FileWriter artiklWritter = new FileWriter(FILE_ARTIKLI, true);
		    PrintWriter out = new PrintWriter(new BufferedWriter(artiklWritter))) {
		out.print(tekst);
		List<String> listaLinijaKategorija = Files.readAllLines(Paths.get(FILE_KATEGORIJE),
			StandardCharsets.UTF_8);
		String stringIdArtikli=listaLinijaKategorija.get(2)+" "+artikl.getId().toString();
		listaLinijaKategorija.set(2, stringIdArtikli);
		Files.write(Paths.get(FILE_KATEGORIJE), listaLinijaKategorija, StandardCharsets.UTF_8);
	    } catch (IOException e) {
		System.err.println(e);
	    }

	}
	if (artikl instanceof Stan) {
	    tekst += "\n3\n";
	    tekst += artikl.getId().toString() + "\n";
	    tekst += artikl.getNaslov() + "\n";
	    tekst += artikl.getOpis() + "\n";
	    tekst += Integer.toString(((Stan) artikl).getKvadratura())+ "\n";
	    tekst += Stanje.fromStanje(artikl.getStanje()) + "\n";
	    tekst += artikl.getCijena().toString();
	    try (FileWriter artiklWritter = new FileWriter(FILE_ARTIKLI, true);
		    PrintWriter out = new PrintWriter(new BufferedWriter(artiklWritter))) {
		out.print(tekst);
		List<String> listaLinijaKategorija = Files.readAllLines(Paths.get(FILE_KATEGORIJE),
			StandardCharsets.UTF_8);
		String stringIdArtikli=listaLinijaKategorija.get(5)+" "+artikl.getId().toString();
		listaLinijaKategorija.set(5, stringIdArtikli);
		Files.write(Paths.get(FILE_KATEGORIJE), listaLinijaKategorija, StandardCharsets.UTF_8);
	    } catch (IOException e) {
		System.err.println(e);
	    }

	}
	if (artikl instanceof Usluga) {
	    tekst += "\n1\n";
	    tekst += artikl.getId().toString() + "\n";
	    tekst += artikl.getNaslov() + "\n";
	    tekst += artikl.getOpis() + "\n";
	    tekst += Stanje.fromStanje(artikl.getStanje()) + "\n";
	    tekst += artikl.getCijena().toString();
	    try (FileWriter artiklWritter = new FileWriter(FILE_ARTIKLI, true);
		    PrintWriter out = new PrintWriter(new BufferedWriter(artiklWritter))) {
		out.print(tekst);
		List<String> listaLinijaKategorija = Files.readAllLines(Paths.get(FILE_KATEGORIJE),
			StandardCharsets.UTF_8);
		String stringIdArtikli=listaLinijaKategorija.get(8)+" "+artikl.getId().toString();
		listaLinijaKategorija.set(8, stringIdArtikli);
		Files.write(Paths.get(FILE_KATEGORIJE), listaLinijaKategorija, StandardCharsets.UTF_8);
	    } catch (IOException e) {
		System.err.println(e);
	    }

	}
    }

    public static void zapisiUDatotekuKorisnika(List<Korisnik> listaKorisnika) {
	String tekst = "";
	Korisnik korisnik = listaKorisnika.get(listaKorisnika.size() - 1);
	if(korisnik instanceof PrivatniKorisnik) {
	    tekst += "\n1\n";
	    tekst += korisnik.getId().toString() + "\n";
	    tekst += ((PrivatniKorisnik) korisnik).getIme() + "\n";
	    tekst += ((PrivatniKorisnik) korisnik).getPrezime() + "\n";
	    tekst += korisnik.getEmail() + "\n";
	    tekst += korisnik.getTelefon();
	    
	    try (FileWriter korisniWritter = new FileWriter(FILE_KORISNICI, true);
		    PrintWriter out = new PrintWriter(new BufferedWriter(korisniWritter))) {
		out.print(tekst);
	    } catch (IOException e) {
		System.err.println(e);
	    }
	}
	if(korisnik instanceof PoslovniKorisnik) {
	    tekst += "\n2\n";
	    tekst += korisnik.getId().toString() + "\n";
	    tekst += ((PoslovniKorisnik) korisnik).getNaziv() + "\n";
	    tekst += ((PoslovniKorisnik) korisnik).getWeb() + "\n";
	    tekst += korisnik.getEmail() + "\n";
	    tekst += korisnik.getTelefon();
	    
	    try (FileWriter korisnikWritter = new FileWriter(FILE_KORISNICI, true);
		    PrintWriter out = new PrintWriter(new BufferedWriter(korisnikWritter))) {
		out.print(tekst);
	    } catch (IOException e) {
		System.err.println(e);
	    }
	}
    }
}
