import numpy as np
from .models import Quota, Agent, Reservation, Tour


def calculate_quota():
    tours = Tour.objects.all()
    agents = Agent.objects.all()

    for tour in tours:
        quotas = []
        free_seats = get_free_seats(tour)
        free_seats = np.arange(free_seats)
        tmp = np.array_split(free_seats, agents.count())
        for tmp2 in tmp:
            quotas.append(tmp2.size)

        for i in range(agents.count()):
            add_quota(agents[i], tour, quotas[i])

    return


def add_quota(agent, tour, count):
    quota = Quota(agent=agent, tour=tour, count=count)
    return quota.save()


def clear_quotas():
    return Quota.objects.all().delete()


def get_free_seats(tour):
    ship = tour.ship
    free_seats = ship.seats
    reservations = Reservation.objects.filter(tour=tour)
    for reservation in reservations:
        free_seats -= reservation.count
    return free_seats
