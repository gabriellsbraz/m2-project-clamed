package com.senai.clamed.DEVinPharmacy.services;

import com.senai.clamed.DEVinPharmacy.models.*;
import com.senai.clamed.DEVinPharmacy.repositories.EstoqueRepository;
import com.senai.clamed.DEVinPharmacy.repositories.FarmaciaRepository;
import com.senai.clamed.DEVinPharmacy.repositories.MedicamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InicializacaoService {
    @Autowired
    private FarmaciaRepository farmaciaRepository;

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Transactional
    public ResponseEntity<?> popularDadosIniciais() {

        try {

            FarmaciaModel farmacia1 = criarFarmacia(90561736000121L, "DevMed Ltda", "Farmácia DevMed",
                    "devmed@farmacia.com", "(44)4444-4444", "(44)9444-4441", 88888999L, "Rua Porto Real",
                    67, "Westeros", "Berlim", "SC", 15.23456, 2.8678687);
            farmaciaRepository.save(farmacia1);

            FarmaciaModel farmacia2 = criarFarmacia(43178995000198L, "MedHouse Ltda", "Farmácia MedHouse",
                    "medhouse@farmacia.com", "(55)5555-5555", "(45)95555-5555", 8877799L, "Rua Madrid",
                    76, "Winterfell", "Estocolmo", "SC", 19.254356, 3.8987687);
            farmaciaRepository.save(farmacia2);

            MedicamentoModel medicamento1 = criarMedicamento(1010, "Programapan", "Matrix", "2x ao dia",
                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc eleifend", 111.00F, TipoMedicamento.COMUM);
            medicamentoRepository.save(medicamento1);

            MedicamentoModel medicamento2 = criarMedicamento(7473, "Cafex", "Colombia Farm", "4x ao dia",
                    "Pellentesque non ultricies mauris, ut lobortis elit. Cras nec cursus nibh", 51.50F, TipoMedicamento.COMUM);
            medicamentoRepository.save(medicamento2);

            MedicamentoModel medicamento3 = criarMedicamento(2233, "Estomazol", "Acme", "1x ao dia",
                    "Sed risus mauris, consectetur eget egestas vitae", 22.50F, TipoMedicamento.COMUM);
            medicamentoRepository.save(medicamento3);

            MedicamentoModel medicamento4 = criarMedicamento(8880, "Gelox", "Ice", "2x ao dia",
                    "Quisque quam orci, vulputate sit amet", 11.50F, TipoMedicamento.COMUM);
            medicamentoRepository.save(medicamento4);

            MedicamentoModel medicamento5 = criarMedicamento(5656, "Aspirazol", "Acme", "3x ao dia",
                    "Sed faucibus, libero iaculis pulvinar consequat, augue elit eleifend", 10.50F, TipoMedicamento.CONTROLADO);
            medicamentoRepository.save(medicamento5);

            MedicamentoModel medicamento6 = criarMedicamento(4040, "Propolizol", "Bee", "5x ao dia",
                    "Nunc euismod ipsum purus, sit amet finibus libero ultricies in", 10.50F, TipoMedicamento.CONTROLADO);
            medicamentoRepository.save(medicamento6);

            criarEstoque(farmacia1, medicamento1, 12);
            criarEstoque(farmacia1, medicamento2, 10);
            criarEstoque(farmacia2, medicamento2, 2);
            criarEstoque(farmacia2, medicamento3, 15);
            criarEstoque(farmacia2, medicamento4, 16);
            criarEstoque(farmacia2, medicamento6, 22);
            return ResponseEntity.ok("Data initialization successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, "Data initialization failed"));
        }
    }

    private FarmaciaModel criarFarmacia(Long cnpj, String razaoSocial, String nomeFantasia, String email,
                                        String telefone, String celular, Long cep, String logradouro,
                                        Integer numero, String bairro, String cidade, String estado,
                                        Double latitude, Double longitude) {
        FarmaciaModel farmacia = new FarmaciaModel();
        farmacia.setCnpj(cnpj);
        farmacia.setRazaoSocial(razaoSocial);
        farmacia.setNomeFantasia(nomeFantasia);
        farmacia.setEmail(email);
        farmacia.setTelefone(telefone);
        farmacia.setCelular(celular);

        EnderecoModel endereco = new EnderecoModel();
        endereco.setCep(cep);
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numero);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);
        endereco.setLatitude(latitude);
        endereco.setLongitude(longitude);

        farmacia.setEndereco(endereco);

        return farmacia;
    }

    private MedicamentoModel criarMedicamento(Integer nroRegistro, String nome, String laboratoriooratorio,
                                              String dosagem, String descricao, Float preco, TipoMedicamento tipo) {
        MedicamentoModel medicamento = new MedicamentoModel();
        medicamento.setNroRegistro(nroRegistro);
        medicamento.setNome(nome);
        medicamento.setLaboratorio(laboratoriooratorio);
        medicamento.setDosagem(dosagem);
        medicamento.setDescricao(descricao);
        medicamento.setPreco(preco);
        medicamento.setTipoMedicamento(tipo);

        return medicamento;
    }

    private void criarEstoque(FarmaciaModel farmacia, MedicamentoModel medicamento, int quantidade) {
        EstoqueModel estoque = new EstoqueModel();

        estoque.setCnpj(farmacia.getCnpj());
        estoque.setNroRegistro(medicamento.getNroRegistro());

        estoque.setQuantidade(quantidade);
        estoque.setDataAtualizacao(LocalDateTime.now());

        estoqueRepository.save(estoque);
    }
}
