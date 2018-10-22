﻿using Scrolls.DataAccessObject;
using Scrolls.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Scrolls.Manager.Controllers
{
    public class HomeController : Controller
    {
        // GET: Home
        public ActionResult Index()
        {
            ViewBag.Nome = string.Empty;
            return View();
        }

        public ActionResult LoginPage()
        {
            return View();
        }

        [HttpPostAttribute]
        public ActionResult Login(string s, string n) {
            Funcionario f = new FuncionarioDAO().Login(s, n);
            if (f != null)
            {
                Session["_Id"] = f.Id;
                Session["_Nome"] = f.Nome;
                Session["_Imagem"] = f.Imagem;
                if (f.Gerente == true)
                {
                    Session["Tipo"] = "Administrador";
                }
                else
                {
                    Session["Tipo"] = "Funcionário";
                }
                return RedirectToAction("Index");
            }
            else {
                ModelState.AddModelError("funcionario.LoginError", "Nome e/ou Senha incorretos");
                ViewBag.Nome = n;
                Session["Tipo"] = string.Empty;
                return View("LoginPage");
            }
        }

        public ActionResult Logout()
        {
            Session["_Id"] = 0;
            Session["_Nome"] = string.Empty;
            Session["_Imagem"] = string.Empty;
            return RedirectToAction("LoginPage");
        }
    }
}