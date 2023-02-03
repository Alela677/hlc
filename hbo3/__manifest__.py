# -*- coding: utf-8 -*-
{
    'name': "HBO3",

    'summary': """
         Modulo Mis Series""",

    'description': """
        Practica desarrolo de modulo en Odoo
    """,

    'author': "Alejandro",
    'website': "https://www.odoo.com/documentation/14.0/es/developer/howtos/backend.html",

    # Categories can be used to filter modules in modules listing
    # Check https://github.com/odoo/odoo/blob/14.0/odoo/addons/base/data/ir_module_category_data.xml
    # for the full list
    'category': 'Uncategorized',
    'version': '0.1',

    # any module necessary for this one to work correctly
    'depends': ['base'],

    # always loaded
    'data': [
        # 'security/ir.model.access.csv',
        'views/views.xml',
        'views/templates.xml',
        'report/hbo_series_repor.xml',
    ],
    # only loaded in demonstration mode
    'demo': [
        'demo/demo.xml',
       
    ],
    'application': True,

}
