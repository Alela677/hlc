# -*- coding: utf-8 -*-
# from odoo import http


# class Hbo3(http.Controller):
#     @http.route('/hbo3/hbo3/', auth='public')
#     def index(self, **kw):
#         return "Hello, world"

#     @http.route('/hbo3/hbo3/objects/', auth='public')
#     def list(self, **kw):
#         return http.request.render('hbo3.listing', {
#             'root': '/hbo3/hbo3',
#             'objects': http.request.env['hbo3.hbo3'].search([]),
#         })

#     @http.route('/hbo3/hbo3/objects/<model("hbo3.hbo3"):obj>/', auth='public')
#     def object(self, obj, **kw):
#         return http.request.render('hbo3.object', {
#             'object': obj
#         })
