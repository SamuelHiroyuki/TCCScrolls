﻿using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Scrolls.Entities
{
    public class Cliente
    {
        [Key]
        public int Id { get; set; }
        
        public string Nome { get; set; }

        public string Sobrenome { get; set; }
        
        public string Email { get; set; } 

        public string Imagem { get; set; }
        
        public string CPF { get; set; }
                
        public string Senha { get; set; }

        public virtual IList<Cartao> Cartoes { get; set; }
        public virtual IList<Endereco> Enderecos { get; set; }
        public virtual IList<Venda> Compras { get; set; }
        public virtual IList<Carrinho> Carrinho { get; set; }
    }
}
