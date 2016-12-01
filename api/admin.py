from django.contrib import admin
from .models import Ship, Tour, Quota, Agent, Customer, Reservation

admin.site.register(Ship)
admin.site.register(Tour)
admin.site.register(Quota)
admin.site.register(Agent)
admin.site.register(Customer)
admin.site.register(Reservation)
