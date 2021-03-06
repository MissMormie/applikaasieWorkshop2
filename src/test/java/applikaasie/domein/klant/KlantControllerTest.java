/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package applikaasie.domein.klant;

import applikaasie.domein.klant.adres.Adres;
import applikaasie.domein.klant.adres.KlantHeeftAdres;
import applikaasie.domein.klant.adres.AdresType;
import applikaasie.domein.account.Account;
import applikaasie.domein.klant.adres.AdresRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.hamcrest.CoreMatchers.hasItems;
import org.junit.Test;
import org.junit.Before;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 *
 * @author Sonja
 */
public class KlantControllerTest {
  
  KlantController controller;

  private MockMvc mockMvc;
  private KlantRepository mockKlantRepository;
  private AdresRepository mockAdresRepository;
  
  @Before
  public void setup() {
    mockKlantRepository = mock(KlantRepository.class);
    mockAdresRepository = mock(AdresRepository.class);
    
    controller = new KlantController(mockKlantRepository, mockAdresRepository);
    mockMvc = standaloneSetup(controller).build();
  }
  
  @Test
  public void shouldDeleteKlant() throws Exception {
    int id = 12;
    String url = "/klant/" + id + "/delete";

    // Create expected result
    String viewName = "klant/deleted";
    when(mockKlantRepository.deleteKlantById(id)).thenReturn(true);

    mockMvc.perform(get(url))
           .andExpect(view().name(viewName));
  }
  
  @Test
  public void shouldShowListOfKlanten() throws Exception {
    String url = "/klant/klanten";

    // Create expected result
    String viewName = "klant/klanten";
    List<Klant> expectedKlanten = createKlantList(10);

    // Set reaction of mock repository
    when(mockKlantRepository.getAllKlanten()).thenReturn(expectedKlanten);

    // check result equals expected
    mockMvc.perform(get(url))
           .andExpect(view().name(viewName))
           .andExpect(model().attributeExists("klantLijst"))
           .andExpect(model().attribute("klantLijst", hasItems(expectedKlanten.toArray())));

    
  }

  @Test 
  public void shouldSearchAchternaamNotVoornaam() throws Exception {
    String url = "/klant/klanten?voornaam=&achternaam=achternaam";
    String viewName = "klant/klanten";
    List<Klant> expectedKlanten = createKlantList(10);
    
    // Set reaction of mock repository
    when(mockKlantRepository.getKlantenByAchternaam("achternaam")).thenReturn(expectedKlanten);
    
    // check result equals expected
    mockMvc.perform(get(url))
           .andExpect(view().name(viewName))
           .andExpect(model().attributeExists("klantLijst"))
           .andExpect(model().attribute("klantLijst", hasItems(expectedKlanten.toArray())));

  }
  
  @Test 
  public void shouldSearchVoornaamNotAchternaam() throws Exception {
    String url = "/klant/klanten?voornaam=voornaam&achternaam=";
    String viewName = "klant/klanten";
    List<Klant> expectedKlanten = createKlantList(10);
    
    // Set reaction of mock repository
    when(mockKlantRepository.getKlantenByVoornaam("voornaam")).thenReturn(expectedKlanten);
    
    // check result equals expected
    mockMvc.perform(get(url))
           .andExpect(view().name(viewName))
           .andExpect(model().attributeExists("klantLijst"))
           .andExpect(model().attribute("klantLijst", hasItems(expectedKlanten.toArray())));

  }  

  @Test 
  public void shouldSearchVoornaamEnAchternaam() throws Exception {
    String url = "/klant/klanten?voornaam=voornaam&achternaam=achternaam";
    String viewName = "klant/klanten";
    List<Klant> expectedKlanten = createKlantList(10);
    
    // Set reaction of mock repository
    when(mockKlantRepository.getKlantenByVoornaamEnAchternaam("voornaam", "achternaam")).thenReturn(expectedKlanten);
    
    // check result equals expected
    mockMvc.perform(get(url))
           .andExpect(view().name(viewName))
           .andExpect(model().attributeExists("klantLijst"))
           .andExpect(model().attribute("klantLijst", hasItems(expectedKlanten.toArray())));

  }  
  
  @Test 
  public void shouldSearchNotVoornaamEnNotAchternaam() throws Exception {
    String url = "/klant/klanten?voornaam=&achternaam=";
    String viewName = "klant/klanten";
    List<Klant> expectedKlanten = createKlantList(10);
    
    // Set reaction of mock repository
    when(mockKlantRepository.getAllKlanten()).thenReturn(expectedKlanten);
    
    // check result equals expected
    mockMvc.perform(get(url))
           .andExpect(view().name(viewName))
           .andExpect(model().attributeExists("klantLijst"))
           .andExpect(model().attribute("klantLijst", hasItems(expectedKlanten.toArray())));

  }    
  
  
  // ------------------- HELPER FUNCTIONS -------------------------------------

   // TODO remove this function once I've implemented database connection.    
  private List<Klant> createKlantList(int count) {
    List<Klant> klantList = new ArrayList<>();
    for (int i = 0; i < count; i++) {
      klantList.add(createKlant(i));
    }
    return klantList;
  }      
  
  private Klant createKlant(int i) {
    Klant klant = new Klant(i, "Marco" + i, "Laan", "van der", "0612345678", "email@gmail.com", false);
    Set<KlantHeeftAdres> kha = createKlantHeeftAdressen(klant);
    klant.setAdressen(kha);
    return klant;
  }

  private Adres createAdres(int i) {
    return new Adres(i, "straat" + i, 12, "", "2403XW", "Alphen aan den Rijn", false);
  }
  
  private Set<KlantHeeftAdres> createKlantHeeftAdressen(Klant klant) {
    Set<KlantHeeftAdres> khaSet = new HashSet<>(3); 
    khaSet.add(new KlantHeeftAdres(1, klant, createAdres(1), AdresType.BEZORGADRES));
    khaSet.add(new KlantHeeftAdres(2, klant, createAdres(2), AdresType.FACTUURADRES));
    khaSet.add(new KlantHeeftAdres(3, klant, createAdres(3), AdresType.POSTADRES));
    return khaSet;
  }
}
