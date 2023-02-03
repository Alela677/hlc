# -*- coding: utf-8 -*-

# from odoo import models, fields, api


# class hbo3(models.Model):
#     _name = 'hbo3.hbo3'
#     _description = 'hbo3.hbo3'

#     name = fields.Char()
#     value = fields.Integer()
#     value2 = fields.Float(compute="_value_pc", store=True)
#     description = fields.Text()
#
#     @api.depends('value')
#     def _value_pc(self):
#         for record in self:
#             record.value2 = float(record.value) / 100

from odoo import models, fields, api
class seriesHBO3(models.Model):
    _name='hboseries'
    _description='serieshbo'

    name=fields.Char('Titulo',required = True)
    director=fields.Char('Director')
    duracion=fields.Integer('Duracion' , defaul= 0)
    fecha=fields.Date('Fecha Lanzamiento')
    nota=fields.Integer('Calificacion',default=0)
    industria=fields.Selection(string='Industria' , selection=[('E','Europa'),('Am','America'),('As','Asia')])
    genero=fields.Many2one('hbogenero', string='Genero')


class generoHBO3(models.Model):
    _name='hbogenero'
    _description='generohbo'

    name=fields.Char('Genero',required = True)